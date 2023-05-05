package com.bjqg.generator.until;

import com.bjqg.web.core.common.Constants;
import org.apache.velocity.app.Velocity;

import java.util.Properties;

/**
 * @author: lbj
 * @date: 2023/5/5 15:50
 */
public class VelocityInitializer {
    /**
     * 初始化vm方法
     */
    public static void initVelocity()
    {
        Properties p = new Properties();
        try
        {
            // 加载classpath目录下的vm文件
            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, Constants.UTF8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
