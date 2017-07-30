package org.crunchytorch.coddy.user.api;


import com.shazam.shazamcrest.MatcherAssert;
import com.shazam.shazamcrest.matcher.Matchers;
import org.apache.commons.lang.StringUtils;
import org.crunchytorch.coddy.Main;
import org.crunchytorch.coddy.application.data.Response;
import org.crunchytorch.coddy.application.utils.TestUtils;
import org.crunchytorch.coddy.user.data.in.Credential;
import org.crunchytorch.coddy.user.data.out.SimpleUser;
import org.crunchytorch.coddy.user.data.security.Token;
import org.crunchytorch.coddy.user.elasticsearch.entity.UserEntity;
import org.crunchytorch.coddy.user.elasticsearch.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = Main.class)
public class UserTest {

    private static final String USER_ENDPOINT = "/user";

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    private UserRepository repository;

    @Before
    public void populateDataBase() throws IOException {
        UserEntity[] users = TestUtils.getObjecFromJson("user/user.database.json", UserEntity[].class);

        repository.save(Arrays.asList(users));
    }

    @After
    public void dropDatabase() {
        repository.deleteAll();
    }

    @Test
    public void testGetUserWithoutToken() {
        ResponseEntity<Response> response =
                restTemplate.getForEntity(TestUtils.getUrl(USER_ENDPOINT + "/napoleon"), Response.class);

        Response expected = new Response("HTTP 401 Unauthorized");

        Assert.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        MatcherAssert.assertThat(response.getBody(), Matchers.sameBeanAs(expected));
    }

    @Test
    public void testGetUserWithToken() throws IOException {
        HttpEntity<String> entity = getHttpEntityWithToken("ciceron", "tutu");

        ResponseEntity<SimpleUser> response = restTemplate.exchange(TestUtils.getUrl(USER_ENDPOINT + "/ciceron"), HttpMethod.GET, entity, SimpleUser.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        MatcherAssert.assertThat(response.getBody(), Matchers.sameBeanAs(TestUtils.getObjecFromJson("user/getUserWithTokenExpected.json", SimpleUser.class)));
    }

    @Test
    public void testCreateUser() {
        final String login = "perlinpinpin";
        Credential credential = new Credential(login, "ratata".toCharArray());
        ResponseEntity<SimpleUser> response = restTemplate.postForEntity(TestUtils.getUrl(USER_ENDPOINT), credential, SimpleUser.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(login, response.getBody().getLogin());
        // test that the current user is effectively in the database
        Assert.assertEquals(this.repository.findByLogin(login).getLogin(), response.getBody().getLogin());
    }

    @Test
    public void testDeleteUserWithToken() {
        final String login = "ciceron";
        HttpEntity<String> entity = getHttpEntityWithToken(login, "tutu");
        ResponseEntity<String> response = restTemplate.exchange(TestUtils.getUrl(USER_ENDPOINT + "/ciceron"), HttpMethod.DELETE, entity, String.class);

        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // check if the user is effectively deleted.
        UserEntity user = repository.findByLogin(login);
        Assert.assertEquals(user, null);
    }

    @Test
    public void testDeleteUserWithAdminPermission() {
        final String login = "gulp";
        final String userToDelete = "ciceron";
        HttpEntity<String> entity = getHttpEntityWithToken(login, "toto");
        ResponseEntity<String> response = restTemplate.exchange(TestUtils.getUrl(USER_ENDPOINT + "/" + userToDelete), HttpMethod.DELETE, entity, String.class);

        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // check if the user is effectively deleted.
        UserEntity user = repository.findByLogin(userToDelete);
        Assert.assertEquals(user, null);
    }

    @Test
    public void testDeleteUserUnauthorized() {
        final String login = "napoleon";
        final String userToDelete = "ciceron";
        HttpEntity<String> entity = getHttpEntityWithToken(login, "toto");
        ResponseEntity<String> response = restTemplate.exchange(TestUtils.getUrl(USER_ENDPOINT + "/" + userToDelete), HttpMethod.DELETE, entity, String.class);

        Assert.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

        // check if the user is effectively deleted.
        UserEntity user = repository.findByLogin(userToDelete);
        Assert.assertTrue(user != null);
    }

    @Test
    public void testDeleteWithoutToken() {
        ResponseEntity<Response> response =
                restTemplate.exchange(TestUtils.getUrl(USER_ENDPOINT + "/napoleon"), HttpMethod.DELETE, null, Response.class);

        Response expected = new Response("HTTP 401 Unauthorized");

        Assert.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        MatcherAssert.assertThat(response.getBody(), Matchers.sameBeanAs(expected));
    }

    @Test
    public void testAuthWithCorrectUser() {
        ResponseEntity<Token> response = this.auth("ciceron", "tutu", Token.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(true, response.getBody() != null && StringUtils.isNotEmpty(response.getBody().getToken()));
    }

    @Test
    public void testAuthWithWrongCrendential() {
        ResponseEntity<Response> response = this.auth("ciceron", "toto", Response.class);
        Response expected = new Response(Response.WRONG_CREDENTIAL);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        MatcherAssert.assertThat(response.getBody(), Matchers.sameBeanAs(expected));
    }

    @Test
    public void testAuthWithNonExistantUser() {
        ResponseEntity<Response> response = this.auth("perlinpinpin", "toto", Response.class);
        Response expected = new Response(Response.WRONG_CREDENTIAL);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        MatcherAssert.assertThat(response.getBody(), Matchers.sameBeanAs(expected));
    }

    private HttpEntity<String> getHttpEntityWithToken(final String login, final String password) {
        Token token = this.auth(login, password, Token.class).getBody();
        HttpHeaders headers = new HttpHeaders();
        headers.add(javax.ws.rs.core.HttpHeaders.AUTHORIZATION, token.getToken());
        headers.add(javax.ws.rs.core.HttpHeaders.CONTENT_TYPE, javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new HttpEntity<>("parameters", headers);
    }

    private <T> ResponseEntity<T> auth(final String login, final String password, Class<T> responseType) {
        Credential credential = new Credential(login, password.toCharArray());

        return restTemplate.postForEntity(TestUtils.getUrl(USER_ENDPOINT + "/auth"), credential, responseType);
    }
}
