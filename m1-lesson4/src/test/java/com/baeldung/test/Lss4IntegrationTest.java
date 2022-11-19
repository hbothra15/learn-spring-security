package com.baeldung.test;

import static org.junit.Assert.assertTrue;

import com.baeldung.lss.spring.LssApp4;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LssApp4.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class Lss4IntegrationTest {

    @Test
    public void whenLoadApplication_thenSuccess() {
        assertTrue(true);
    }
}
