package com.baeldung.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.baeldung.lss.spring.LssApp6;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LssApp6.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class Lss6IntegrationTest {

    @Test
    public void whenLoadApplication_thenSuccess() {
        assertTrue(true);
    }
}
