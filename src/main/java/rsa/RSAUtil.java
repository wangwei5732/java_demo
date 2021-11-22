package rsa;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author 马永振
 * @date 2021年08月11日
 */
public class RSAUtil {
    private static String PUBLIC_KEY;
    private static String PRIVATE_KEY;

    /**
     * 随机生成密钥对
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 打印生成的公钥、私钥
        System.out.println("随机生成的公钥为: " + publicKeyString);
        System.out.println("随机生成的私钥为: " + privateKeyString);
    }
    /**
     * RSA公钥加密
     * @param str 加密字符串
     * @return 密文
     * @throws Exception  加密过程中的异常信息
     */
    public static String encrypt( String str ) throws Exception{
        if(StringUtils.isBlank(PUBLIC_KEY)){
            PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCr6AwqBZH1nuoLgFxPuTM+z36NYJce5TFLyhJLouBI28RDUyc/wwxO51uoyuCW9B9w1Ovt4iwjUitanpd4F4+QlYnhheDAHOfxyogBZ7Q6cSFKpEVwJH3ySAU0YcryQK2RAy8wsf4iEj7D8bWG91EgWWsOLGEM6GaSVh7GkPAUpwIDAQAB";
        }
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(PUBLIC_KEY);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     * @param str 加密字符串
     * @return 明文
     * @throws Exception  解密过程中的异常信息
     */
    public static String decrypt(String str) throws Exception{
        if(StringUtils.isBlank(PRIVATE_KEY)){
            PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKvoDCoFkfWe6guAXE+5Mz7Pfo1glx7lMUvKEkui4EjbxENTJz/DDE7nW6jK4Jb0H3DU6+3iLCNSK1qel3gXj5CVieGF4MAc5/HKiAFntDpxIUqkRXAkffJIBTRhyvJArZEDLzCx/iISPsPxtYb3USBZaw4sYQzoZpJWHsaQ8BSnAgMBAAECgYBkWDRZhxzXIib+BbRcuP9nx5jYj6xG1YQ7+D1Lt0xyWw4qOmyrtIAHwtbnJV2J4ABGTZNWLXKUP4+c+vvxltcul9/ZS1NHYG3J9j0/ZYJ/bBUSVJ4SdOnwQ6yUpxqXhHxOF44cDrUGUZ06Y4l0MqtgHLc2dmUqEMTA5JTnqNsw8QJBAPO9VUB5elWMe0dhC3FU/vXMuAK9tttK24Zj4B0J3Sytfqoj3s2jj8g0PNwIHoIGnUfVvJliLdvMo5SW6q0CnHkCQQC0jbWjtYKOjhW2mTAYy8hesThJBRp3YMKZ5rV/cNyobAI8DavdxmsjGpF3JzWxO8xB/ZWiQXWr+JDJG3mIq7IfAkBGw3pDzMDyo2WLNKaQ0b9v926ZBSFYi2QuoUKIy7tV5Ez48o8WHt14n4nny8ensmnpWCl4UqPDw6beVe5uKmrpAkEAgx46H2YMJsxGNnGuvQMYQrP+RQDgxHrPN4aUnbTveZveT5A6FHcojLiZlOczlb7wSagHR2q0MqvzMSizrK3LTwJBAM2Evz7btDSwuEvxYyWLiN/XQm+qcTcunCqCj5asrgpYQUAB6p9wI9xBtzd4N2BhNQb7qZx9ZAAGsS6KT7hPCGc=";
        }
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(PRIVATE_KEY);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }


    public static void main(String[] args) throws Exception {
//        RSAUtil.genKeyPair();
                //待加密的字符串
        String message = "shayuyu";
        System.out.println("加密前："+message);
        long start = System.currentTimeMillis();
        String messageEn = encrypt(message);
        System.out.println("加密耗时："+(System.currentTimeMillis()-start)+"ms");
        System.out.println( "加密后的字符串为: " + messageEn);
        start = System.currentTimeMillis();
        String messageDe = decrypt("RkkISoMwaxB9n4P/VU+cnSBXukpFegMVeMk1eCM3Nti2cf0NEs2vz9Kuyw2K8qGK6tqZXFzpMgfJ5re/CjxMoft+4baMfHAs9x4DGW7YnPUNn+o7GX/MmWXFbA0BY3ZNR7ThWtDFdc6WTc2f0BHLvazFYE+thCEygpOW9zWkXIw=");
        System.out.println("解密耗时："+(System.currentTimeMillis()-start)+"ms");
        System.out.println("解密后的字符串为: " + messageDe);
    }
}
