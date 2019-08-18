package com.soplong.bolgs;

import com.soplong.bolgs.constant.shiro.ShiroConfig;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BolgsApplicationTests {

    /**
     * 获取全局的配置信息（上下文）
     */
    @Autowired
    ApplicationContext ioc;

    @Test
    public void contextLoads() {
        String hashAlgorithmName = "MD5";
        String credentials = "123456";
        int hashIterations = 1024;
        Object obj = new SimpleHash(hashAlgorithmName, credentials,"",hashIterations);
        System.out.println(obj.toString());
    }

}
