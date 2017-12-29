package org.crunchytorch.coddy.language.api;

import org.crunchytorch.coddy.Main;
import org.crunchytorch.coddy.application.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = Main.class)
public class LanguageWithoutPropertiesTest {

    private static final String LANGUAGE_ENDPOINT = "/language";

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void getLanguagesTest() {
        ResponseEntity<String[]> response = this.restTemplate.getForEntity(TestUtils.getUrl(LANGUAGE_ENDPOINT), String[].class);
        String[] expected = new String[]{"java", "cpp", "python","go"};

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertArrayEquals(expected, response.getBody());
    }
}
