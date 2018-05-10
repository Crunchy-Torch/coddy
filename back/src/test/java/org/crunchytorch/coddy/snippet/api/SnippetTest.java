package org.crunchytorch.coddy.snippet.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.shazam.shazamcrest.MatcherAssert;
import com.shazam.shazamcrest.matcher.Matchers;
import org.crunchytorch.coddy.Main;
import org.crunchytorch.coddy.application.data.Page;
import org.crunchytorch.coddy.application.utils.TestUtils;
import org.crunchytorch.coddy.snippet.elasticsearch.entity.SnippetEntity;
import org.crunchytorch.coddy.snippet.elasticsearch.repository.SnippetRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Main.class)
public class SnippetTest {
    private static final String SNIPPET_ENDPOINT = "/snippet";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SnippetRepository repository;

    @Before
    public void populateDataBase() throws IOException {
        SnippetEntity[] snippets = TestUtils.getObjectFromJson("snippet/content.json", SnippetEntity[].class);

        repository.save(Arrays.asList(snippets));
    }

    @After
    public void dropDatabase() {
        repository.deleteAll();
    }

    @Test
    public void testGetSnippet() throws IOException {
        ResponseEntity<String> response = restTemplate.getForEntity(SNIPPET_ENDPOINT, String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Page<SnippetEntity> page = TestUtils.getObjectFromString(response.getBody(), new TypeReference<Page<SnippetEntity>>() {
        });

        List<SnippetEntity> snippetsExpected = Arrays.asList(TestUtils.getObjectFromJson("snippet/content.json", SnippetEntity[].class));

        Assert.assertEquals(2, page.getTotalElement());
        Assert.assertEquals(1, page.getTotalPage());
        MatcherAssert.assertThat(page.getHits(), Matchers.sameBeanAs(snippetsExpected).ignoring("_id"));
    }
}
