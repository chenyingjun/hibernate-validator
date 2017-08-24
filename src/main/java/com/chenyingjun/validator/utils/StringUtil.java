package com.chenyingjun.validator.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: StringUtil.java
 * @Package com.red.p2p.util
 * @Description: 字符串工具类
 * @author: chenyingjun
 * @date: 2017年8月24日
 * @version V1.0
 */
public abstract class StringUtil {

    /**
     * 
     * @Title: transMapToString
     * @Description: map转为String
     * @param map
     *            map
     *
     * @return
     */
    public static String transMapToString(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer("Map [");

        for (Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey().toString()).append("=")
                    .append(null == entry.getValue() ? "" : entry.getValue().toString()).append(",");
        }

        String str = "";

        if (sb.toString().endsWith(",")) {
            str = sb.substring(0, sb.length() - 1);
        }

        str = str + "]";

        return str;
    }

    /**
     * 
     * @Title: getRandomString
     * @Description:生成随机字符串
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String getRandomString(int length) {

        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

        Random random = new Random();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; i++) {

            int number = random.nextInt(61);

            sb.append(str.charAt(number));
        }

        return sb.toString();
    }

    /**
     * 
     * @Title: getRandomNum
     * @Description: 生成随机数字
     * @param length
     *            随机数串长度
     * @return 随机数
     */
    public static String getRandomNum(int length) {

        String str = "0123456789";
        Random random = new Random();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; i++) {

            int number = random.nextInt(10);

            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 
     * @Title: md516
     * @Description: 16位md5加密
     * @param text
     *            需要加密的字符串
     * @return 加密后的字符串小写
     */
    public static String md516(String text) throws Exception {

        StringBuffer hexString = new StringBuffer();

        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(text.getBytes());

        byte[] digest = md.digest();

        for (int i = 0; i < digest.length; i++) {

            text = Integer.toHexString(0xFF & digest[i]);

            if (text.length() < 2) {

                text = "0" + text;
            }

            hexString.append(text);
        }

        return hexString.substring(8, 24).toLowerCase();
    }

    /**
     * 
     * @Title: md532
     * @Description: 32位md5加密
     * @param text
     *            需要加密的字符串
     * @return 加密后的字符串小写
     */
    public static String md532(String text) throws Exception {

        StringBuffer hexString = new StringBuffer();

        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(text.getBytes());

        byte[] digest = md.digest();

        for (int i = 0; i < digest.length; i++) {

            text = Integer.toHexString(0xFF & digest[i]);

            if (text.length() < 2) {

                text = "0" + text;
            }

            hexString.append(text);
        }

        return hexString.toString().toLowerCase();
    }

    /**
     * 
     * @Title: getInputStreamMd5
     * @Description: 获取输入流的md5值
     * @param inputStream
     *            输入流
     * @return md5值
     */
    public static String getInputStreamMd5(InputStream inputStream) throws NoSuchAlgorithmException, IOException {

        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] buffer = new byte[1024000];

        int length;

        while ((length = inputStream.read(buffer)) != -1) {

            md.update(buffer, 0, length);
        }

        return new String(Hex.encodeHex(md.digest())).toLowerCase();
    }

    /**
     * 
     * @Title: getUUID
     * @Description: 生成32位的UUID
     * @return 32位的UUID
     */
    public static final String getUUID() {

        UUID object = UUID.randomUUID();

        String uuid = object.toString();

        uuid = uuid.replaceAll("-", "");

        return uuid;
    }

    /**
     * countSonInMother 统计mother包含son的个数
     * 
     * @param mother
     * @param son
     * @return
     */
    public static int countSonInMother(String mother, String son) {
        int count = 0;
        if (mother.indexOf(son) != -1) {
            Matcher match = Pattern.compile(son).matcher(mother);
            while (match.find()) {
                count++;
            }
        }
        return count;
    }

    /**
     * @Title: checkXss
     * @Description: 验证value是否包含Xss 包含返回false不包含返回true
     * 
     * @return
     * @throws Exception
     */
    public static boolean checkXss(String value) {

        if (StringUtils.isBlank(value)) {

            return true;
        }

        // NOTE: It's highly recommended to use the ESAPI library and uncomment
        // the following line to
        // avoid encoded attacks.
        // value = ESAPI.encoder().canonicalize(value);
        // Avoid null characters
        // Avoid anything between script tags
        Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        // Avoid anything in a src="http://www.yihaomen.com/article/java/..."
        // type of e­xpression
        scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                | Pattern.DOTALL);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                | Pattern.DOTALL);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }
        // Remove any lonesome </script> tag
        scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        // Remove any lonesome <script ...> tag
        scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        // Avoid eval(...) e­xpressions
        scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                | Pattern.DOTALL);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        // Avoid e­xpression(...) e­xpressions
        scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                | Pattern.DOTALL);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        // Avoid javascript:... e­xpressions
        scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        // Avoid vbscript:... e­xpressions
        scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        // Avoid onload= e­xpressions
        scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        try {
            value = value.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            value = value.replaceAll("\\+", "%2B");
            String value_ = URLDecoder.decode(value, "UTF-8");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                    | Pattern.DOTALL);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                    | Pattern.DOTALL);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

            scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                    | Pattern.DOTALL);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                    | Pattern.DOTALL);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

        } catch (UnsupportedEncodingException e) {

        }

        return true;
    }
    
    /**
     * 
     * @Title: encrypt
     * @Description: 数字类型的字符串加密
     * @return Long
     */
    public static Long encrypt(String str){
    	Long i = 0L;
    	if(str.indexOf("DTL") >= 0){
    		str = str.replace("DTL", "");
        	int offset = 6;//右移位数
        	int radix = 10;//设置为10，表示10进制，16表示16进制
        	int size = 2;//放大倍数,由于long的最大值为9223372036854775807，订单位数为16位（最大9999999999999999），因此放大倍数以不超过1000为好
        	i = Long.parseLong(str, radix)*size << offset;//将字符串按radix进制转换成数字，放大size倍，然后右移offset位;
    	}
    	return i;
    }
    
    /**
     * 
     * @Title: dencrypt
     * @Description: 数字解密
     * @return String
     */
    public static String dencrypt(Long i){
    	String str = null;
    	if(i != 0){
    		int offset = 6;//左移位数（与加密时的右移位数相同）
        	int radix = 10;//设置为10，表示10进制，16表示16进制（与加密时的进制相同）
        	int size = 2;//缩小倍数
        	i = (i >> offset)/size;
        	str = "DTL"+ Long.toString(i, radix);
    	}
    	return str;
    }

    /**
     * checkMobile 验证是否为手机号码
     * 
     * @param mobile
     * @return
     */
    public static boolean checkMobile(String mobile) {
        Pattern p = Pattern.compile("^1\\d{10}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
    
    /**
     * checkAmount 验证是否为金额格式
     * 
     * @param amount amount
     * @return
     */
    public static boolean checkAmount(String amount) {
        Pattern p = Pattern.compile("^(([1-9]{1}[0-9]{0,15})|0)(\\.[0-9]{1,2})?$");
        Matcher m = p.matcher(amount);
        return m.matches();
    }

    /**
     * checkInteger 验证是否为Integer
     * 
     * @param id
     * @return
     */
    public static boolean checkInteger(String id) {
        Pattern p = Pattern.compile("^[1-9]{1}[0-9]{0,10}$");
        Matcher m = p.matcher(id);
        return m.matches();
    }

    /**
     * checkCode 验证是否为融资包编号
     * 
     * @param code
     * @return
     */
    public static boolean checkCode(String code) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9]{10,32}$");
        Matcher m = p.matcher(code);
        return m.matches();
    }

    /**
     * checkMobiles 验证是否为手机号码
     * 
     * @param mobile
     * @return
     */
    public static boolean checkMobiles(String mobile) {
        Pattern p = Pattern.compile("^1\\d{10}(\\,1\\d{10}){1,49}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
}