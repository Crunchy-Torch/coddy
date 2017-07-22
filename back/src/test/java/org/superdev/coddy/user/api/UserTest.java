package org.superdev.coddy.user.api;


import com.shazam.shazamcrest.MatcherAssert;
import com.shazam.shazamcrest.matcher.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.superdev.coddy.Main;
import org.superdev.coddy.application.data.Response;
import org.superdev.coddy.application.utils.TestUtils;
import org.superdev.coddy.user.data.Credential;
import org.superdev.coddy.user.data.Token;
import org.superdev.coddy.user.elasticsearch.entity.UserEntity;
import org.superdev.coddy.user.elasticsearch.repository.UserRepository;

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

        ResponseEntity<UserEntity> response = restTemplate.exchange(TestUtils.getUrl(USER_ENDPOINT + "/ciceron"), HttpMethod.GET, entity, UserEntity.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        MatcherAssert.assertThat(response.getBody(), Matchers.sameBeanAs(TestUtils.getObjecFromJson("user/getUserWithTokenExpected.json", UserEntity.class)));
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

    private HttpEntity<String> getHttpEntityWithToken(final String login, final String password) {
        Credential credential = new Credential(login, password.toCharArray());
        Token token = restTemplate.postForEntity(TestUtils.getUrl(USER_ENDPOINT + "/auth"), credential, Token.class).getBody();
        HttpHeaders headers = new HttpHeaders();
        headers.add(javax.ws.rs.core.HttpHeaders.AUTHORIZATION, token.getToken());
        headers.add(javax.ws.rs.core.HttpHeaders.CONTENT_TYPE, javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new HttpEntity<>("parameters", headers);
    }
}
