package com.tan.solr;

import com.tan.solr.config.SolrCustomConfig;
import com.tan.solr.service.SolrCreateAction;
import com.tan.solr.service.SolrDeleteAction;
import com.tan.solr.service.SolrEditAction;
import com.tan.solr.service.SolrQueryAction;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.security.Sha256AuthenticationProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

@EnableConfigurationProperties // 启用配置类功能
@SpringBootApplication
public class Application {

    public Application(SolrQueryAction solrQueryAction, SolrCreateAction solrCreateAction,
                       SolrEditAction solrEditAction, SolrDeleteAction solrDeleteAction, SolrCustomConfig config) throws SolrServerException, IOException {
//        solrQueryAction.queryByParameter();
//        solrQueryAction.simpleQuery();
//        solrCreateAction.addDocument();
//        solrCreateAction.batchAdd();
        solrEditAction.editDoc();
//        solrDeleteAction.deleteDocById("7");
//        solrDeleteAction.deleteByQuery();
//        solrCreateAction.addByBean();
//        solrQueryAction.searchByBean();

//        String password = config.getPassword();
//        String hashPwd = Sha256AuthenticationProvider.getSaltedHashedValue(password);

//       manualHashValue(password);

    }

    /**
     * 自定义的hash操作
     * @param pwd
     */
    public void manualHashValue(String pwd) {
        String hashPwd = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //随机生成盐值
            Random r = new SecureRandom();
            byte[] salt = new byte[32];
            r.nextBytes(salt);
            //盐值进行base64加密
            String saltBase64 = Base64.getEncoder().encodeToString(salt);
            if (saltBase64 != null) {
                digest.reset();
                //将原始的盐值加入到哈希计算中
                digest.update(Base64.getDecoder().decode(saltBase64));
            }
            //将密码进行哈希
            byte[] btPass = digest.digest(pwd.getBytes(StandardCharsets.UTF_8));
            digest.reset();
            //二次哈希
            btPass = digest.digest(btPass);
            //对二次哈希后的密码进行base64，并拼接密码和盐值返回
            String encodePwd = Base64.getEncoder().encodeToString(btPass);
            hashPwd = encodePwd + " " + saltBase64;

        } catch (NoSuchAlgorithmException e) {
            System.err.println("未知算法异常：" + e.getMessage());
        }
        System.out.println("生成的密码hash：" + hashPwd);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
