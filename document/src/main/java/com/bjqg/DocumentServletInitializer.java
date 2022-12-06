package com.bjqg;

import com.bjqg.DocumentApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author: lbj
 * @date: 2022/12/6 9:51
 */
public class DocumentServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DocumentApplication.class);
    }
}
