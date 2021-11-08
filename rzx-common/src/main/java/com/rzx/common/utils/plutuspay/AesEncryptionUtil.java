package com.rzx.common.utils.plutuspay;

import com.alibaba.fastjson.JSONObject;
import com.rzx.common.utils.Base64Util;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;


/**
 * AES加密解密算法
 *
 * @author long
 */
public class AesEncryptionUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AesEncryptionUtil.class);

    // /** 算法/模式/填充 **/
    private static final String CIPHER_MODE = "AES/CBC/PKCS5Padding";

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";

    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS7Padding";

    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    /**
     * 密钥
     */
    public static String SECRET_KEY = "5644454475794445464d707a4d69715a";


    // /** 创建密钥 **/
    private static SecretKeySpec createKey(String key) {
        byte[] data = null;
        data = hexStringToByteArray(key);
        return new SecretKeySpec(data, "AES");
    }

    /**
     * 实例化私钥
     *
     * @return
     */
    private static PrivateKey getPrivateKey() {
        PrivateKey privateKey = null;
        String priKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDctoGMQslW7djG"
                + "lQVJ45Ln1Dzr0ua8KoElmLs94q7gK4OG+5i2eQ9H6OGvwl5QdYCgz3Z/YyR/SDhP"
                + "oBISaUoNk45Rx8JL99ROLGSzF/IcLpI6eCO6GlWeQNNXPzTD7PaSxGCo08XaP35m"
                + "mq5f80QF4Q9lkolSqSXRZG56T51Qf1F+3YWrBA+kZzpnkwcCzARj1gwFbEgNCXlw"
                + "c2iHkt1WAPlq5iX2jxxpDfsYLVsCa2bLB/a33FQYE0VSmxuwGuppzz+XYiLAEdnn"
                + "Q5ie5RPHTcCscfbh83qLCJQwnUfzrKA7mC0LhA2gMJPkhH8VXE6NE9s9v11mZX35"
                + "UJDYOw5HAgMBAAECggEASXNrNXRccNOCmsOpGOflUcVt6MUtCF3U0OA9H+E6dVvF"
                + "z7wXHFK5ZSL5SHaU0I3qGpowQ0CJOtqb8tPAb+QeQtpiOJET9hgpzUI02O0VI4/U"
                + "FiU5LovpBltoedaNBs7rV/iGemjPpUwQErFTapcYgiRd7V2Q2bN05HRZ19Z68fhu"
                + "qBhrFz/4Jr+U0RtBrBlqlz8PqiLAkxE35kpiYH7tHxawZ97spaUkswX7SfkahKt3"
                + "fxFbYTp+GNtkzwIcFUUVU4rEYrDyEsXqhZKuTAk8FiK1kVMJNGEo/ZnZwmiI0qoc"
                + "BkYeL7MnkGmi/8Umqf2Qg4OBcDDKaGT7fHpD8E1dgQKBgQD1tXERhIX9dqXySzyO"
                + "49GMHHdcyltkILqr7Tj+v2BLDDiqKJz0zGehnFh7vSJ7hPh3kmro4pkyceMtkbLm"
                + "137dszX5QdlmLoQcK9s1AnpLNF1w7iItg09p0cUKP1bmaeDeCQdLD3xQEdQS4AXc"
                + "nUqACQzvWSiRQo0nxmM9zaAJZwKBgQDl9Q1LMIcLsOpkcADkWbmHUZv3342/fcOm"
                + "/sC1Ytyz4aXCeE+SQKVr6guaEj2M//kGPory/mSLgfifygc6ascSvTe8KfKyJhVF"
                + "91mZ4Lx6CE6FpIdMxM1FrOfl6pS6SPIcApqYoDOx9twrpOyctEIK+dOjzaac5LoI"
                + "AivhsfhoIQKBgQDA66qQh44w+fTH7jzpMIU6TquFprjlQDFxhnwmwRqZMkpXiDXc"
                + "m5WOtdRfq5wkgb4ZHFRm8Gw6yGMIR4rBZ1gMOIbbY9GPQlymReUClSRyYfYwEjmx"
                + "pJU4z+S27A6OMXnGvC4GyGzh7W2nOFBB7Rout2x5jXalvyxXm5QWVZofZwKBgQC7"
                + "Ve1kib4aqh/pfs1bOjVIphVX08lYbQ5NJ1C69DU0Xel6CiVY0k1xIpQdCisWiRnI"
                + "Gn98Af7LIDD5i85jqsVO4NNc+Rd8RkYiX6WhFFK3X7kIasbGpbVMw9WPmpFbST3j"
                + "z8M9/5TdsGwhKhSQfNEou+nMkibfvf5Hs+0nyqLBwQKBgGBeqvvot9+9ev2nZNAh"
                + "qINy5+qfx6w05Sm2NZRd00kyTzXh+Y5iTr80xcNLK1hRqlKY+S/tOH/Qk6+jaF41"
                + "hpk8N8llX6vKkDrIplULHgrsoYEh8g4SWYE0yG78Npu3c+T2LZf9alDSu/ZhCwNe"
                + "jg9Zs6eRaeEwTGaGI1XxZVVA";

        try {

            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(priKey.getBytes()));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            privateKey = keyf.generatePrivate(priPKCS8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privateKey;
    }


    //实例化公钥
    public static PublicKey getPublicKey() {
        PublicKey publicKey = null;
        String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw7vtRyubRNr/DAAJ7IsKICgRNKTkELDfA1lmMyOxxPjbsPVOO9i1mWa3Ui8dF+RxaonibiL1EwdCYTxrP1B5y/dB4E+nC6cBqmhy3rOA0FWmsE7PHBmEBtgVw6FdKYT9OqFzYNHItq9MdpMpJH6f6DuJwiZMU8Rd9ubMLyN95/ikjRjiFlKfXbU/+tvRASXIquOXf+9ljnNhCecLWp/7KHZRiPTmLa2vUpAen76Qt1hYpmvrKWU5My/9+7b60gtcgDQr7rzO/zR5SIfhNDmk8pGcyhMlq47lFlWukFo4J4DNe5POY7PvGqCF9TL3RhspVmbe29DiduCWDVS2mmizNQIDAQAB";

        try {
            X509EncodedKeySpec PubKeySpec = new X509EncodedKeySpec(
                    Base64.decode(pubKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // 取公钥匙对象
            publicKey = keyFactory.generatePublic(PubKeySpec);
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (InvalidKeySpecException e1) {
            e1.printStackTrace();
        }
        return publicKey;
    }


    // /** 加密字节数据 **/
    public static byte[] encrypt(byte[] content, String password) {
        try {
            byte[] data = hexStringToByteArray(password);
            SecretKeySpec key = new SecretKeySpec(data, "AES");
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[16]));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 解密AES加密过的字符串
     *
     * @param content  AES加密过过的内容
     * @param password 加密时的密码
     * @return 明文
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            byte[] data = hexStringToByteArray(password);
            SecretKeySpec key = new SecretKeySpec(data, "AES");
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encryption(String url) {
        JSONObject object = new JSONObject();
        try {
            byte[] b = url.getBytes("UTF-8");
            //AES加密
            byte[] text = AesEncryptionUtil.encrypt(b, SECRET_KEY);
            String content = Base64.toBase64String(text);

            //签名
            byte[] sign = AesEncryptionUtil.sign256(text, getPrivateKey());
            String signature = Base64.toBase64String(sign);

            object.put("devId", "VDEDuyDE");
            object.put("content", content);
            object.put("signature", signature);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return object.toString();
    }


    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] b = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return b;
    }

    //SHA256withRSA签名
    public static byte[] sign256(byte[] data, PrivateKey privateKey) {
        byte[] signed = null;
        Signature signature = null;
        try {
            signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateKey);
            signature.update(data);
            signed = signature.sign();
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return signed;
    }

    /**
     * SHA256withRSA验签
     * @param data
     * @param sign
     * @return
     */
    public static boolean verify256(String data, byte[] sign) {
        if (data == null || sign == null) {
            return false;
        }
        try {
            Signature signetcheck = Signature.getInstance(SIGNATURE_ALGORITHM);
            signetcheck.initVerify(getPublicKey());
            signetcheck.update(Base64.decode(data));
            return signetcheck.verify(sign);
        } catch (Exception e) {
            return false;
        }
    }


    @SuppressWarnings("unchecked")
    public static void fixKeyLength() {

        String errorString = null;
        int newMaxKeyLength = 0;
        try {
            errorString = "Failed manually overriding key-length permissions.";
            if ((newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES")) < 256) {
                Class<?> c = Class.forName("javax.crypto.CryptoAllPermissionCollection");

                Constructor<?> con = c.getDeclaredConstructor();

                con.setAccessible(true);

                Object allPermissionCollection = con.newInstance();

                Field f = c.getDeclaredField("all_allowed");

                f.setAccessible(true);

                f.setBoolean(allPermissionCollection, true);


                c = Class.forName("javax.crypto.CryptoPermissions");

                con = c.getDeclaredConstructor();

                con.setAccessible(true);

                Object allPermissions = con.newInstance();

                f = c.getDeclaredField("perms");

                f.setAccessible(true);

                ((Map<String, Object>) f.get(allPermissions)).put("*", allPermissionCollection);


                c = Class.forName("javax.crypto.JceSecurityManager");

                f = c.getDeclaredField("defaultPolicy");

                f.setAccessible(true);

                Field mf = Field.class.getDeclaredField("modifiers");

                mf.setAccessible(true);

                mf.setInt(f, f.getModifiers() & ~Modifier.FINAL);

                f.set(null, allPermissions);


                newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES");
            }
        } catch (NoSuchAlgorithmException | ClassNotFoundException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchFieldException e) {
            logger.error(e.getMessage(), e);
        }
        if (newMaxKeyLength < 256) {
            // hack failed
            throw new RuntimeException(errorString);
        }
    }


    /**
     * AES-256-ECB解密
     *
     * @param base64Data 解密字符串
     * @param secretKey  加密密钥 商户key
     * @return
     */
    public static String decryptData(String base64Data, String secretKey) {

        try {
            //对商户key做md5，得到32位小写key*
            SecretKeySpec key = new SecretKeySpec(MD5Encode(secretKey, "UTF-8").toLowerCase().getBytes(), ALGORITHM);

            //添加一个安全管理器提供者
            fixKeyLength();
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

            // 创建密码器
            Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);

            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, key);

            return new String(cipher.doFinal(Base64Util.decode(base64Data)));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }


    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}
