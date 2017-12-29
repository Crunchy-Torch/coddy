package org.crunchytorch.coddy.application.api;

import org.crunchytorch.coddy.Main;
import org.crunchytorch.coddy.application.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
        , classes = Main.class)
public class HelloTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void health() {
        ResponseEntity<String> entity =
                restTemplate.getForEntity(TestUtils.getUrl("/"), String.class);

        Assert.assertEquals("Hello coddy!", entity.getBody());
        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

}
