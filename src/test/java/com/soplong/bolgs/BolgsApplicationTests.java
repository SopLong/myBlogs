package com.soplong.bolgs;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.soplong.bolgs.constant.Constant;
import com.soplong.bolgs.config.shiro.ShiroConfig;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

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

    @Test
    public void uploadQiNiu(){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = Constant.ACCESS_KEY;
        String secretKey = Constant.SECRET_KEY;
        String bucket = Constant.BUCKET;
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "C:\\Users\\Administrator\\Desktop\\申通快递.docx";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    @Test
    public void downFileFromQiNiu(){
        String fileName = "Fsldh5I__YasUyqUgsYbOvvsIESs";
        String domainOfBucket = "pwimq6t47.bkt.clouddn.com";
        String finalUrl = String.format("%s/%s", domainOfBucket, fileName);

        System.out.println(finalUrl);
    }

    @Test
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("index");
        view.addObject("userName", "蝈蝈");
        return view;
    }
}
