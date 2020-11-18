package com.hanzo.client.util;

import com.hanzo.common.exception.TokenException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.util.FileCopyUtils;
import java.io.IOException;
import java.security.*;


/**
 * @Author thy
 * @Date 2020/11/17 17:21
 * @Description: 获取公钥工具类
 */
public class RSAKeyHelper {

    /**
     * 生成的jks文件名 需要放在resource下面
     */
    private static final String fileName = "hanzo.jks";
    /**
     * jks的密码
     */
    private static final String password = "hanzoCloud";
    /**
     * jks的别名
     */
    private static final String alias = "hanzo";

    /**
     * 获取公钥 两种办法 一个从jks  一个从单独导出的一份publicKey.txt获取
     * @return
     */
    public static PublicKey getPublicKey() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(fileName), password.toCharArray());
        PublicKey publicKey = keyStoreKeyFactory.getKeyPair(alias).getPublic();
        return publicKey;
    }

    /**
     * 获取私钥
     * @return
     */
    public static PrivateKey getPrivateKey(){
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(fileName), password.toCharArray());
        PrivateKey privateKey = keyStoreKeyFactory.getKeyPair(alias).getPrivate();
        return privateKey;
    }

    /**
     * 这种是client客户端初始化用的公钥版本
     * @return
     * @throws TokenException
     */
    public static String getInitPublicKey() throws TokenException {
        Resource resource = new ClassPathResource("publicKey.txt");
        String publicKey ;
        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        }catch (IOException e){
            e.printStackTrace();
            throw new TokenException("获取RSA公钥失败");
        }
        return publicKey;
    }
}
