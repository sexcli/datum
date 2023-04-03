package com.bjqg.document;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: lbj
 * @date: 2023/3/1 11:57
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class HttpClientTest {
    @Test
    public void authorizationTest() {

    }
}
