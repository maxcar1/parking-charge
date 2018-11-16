package com.maxcar.base.util.wechat;

import com.maxcar.base.util.MD5Util;
import com.maxcar.base.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 微信工具类
 * yangshujun
 */
public class WeiXinUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeiXinUtils.class);

    private static final String NONE_STR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPKRSTUVWXYZ";
    //定义全局map对象
    public static ConcurrentHashMap<String,Object> tokenMap = new ConcurrentHashMap<>();

    public static final String SIGN = "sign";

    private static Lock lock = new ReentrantLock();
    /**
     * 发送https请求，返回二维码图片
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param data 提交的数据
     * @param savePath 图片保存路径
     * @param fileName 图片名称
     * @param fileType 图片类型
     */
    public static void httpsRequestPicture(String requestUrl, String requestMethod, String data, String savePath, String fileName, String fileType) {
        InputStream inputStream = null;
        try {
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            //设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.connect();
            //当data不为null时向输出流写数据
            if (null != data) {
                //getOutputStream方法隐藏了connect()方法
                OutputStream outputStream = conn.getOutputStream();
                //注意编码格式
                outputStream.write(data.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            inputStream = conn.getInputStream();
            LOGGER.info("开始生成微信二维码...");
            inputStreamToMedia(inputStream, savePath, fileName, fileType);
            LOGGER.info("微信二维码生成成功!!!");
            conn.disconnect();
        } catch (Exception e) {
            LOGGER.error("发送https请求失败，失败", e);
        }finally {
            //释放资源
            try {
                if(null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error("释放资源失败，失败", e);
            }
        }
    }

    /**
     * 将输入流转换为图片
     * @param input 输入流
     * @param savePath 图片需要保存的路径
     * @param fileType 图片类型
     */
    public static void inputStreamToMedia(InputStream input, String savePath, String fileName, String fileType) throws Exception {
        String filePath = savePath + "/" + fileName + "." + fileType;
        File file = new File(filePath);
        FileOutputStream outputStream = new FileOutputStream(file);
        int length;
        byte[] data = new byte[1024];
        while ((length = input.read(data)) != -1) {
            outputStream.write(data, 0, length);
        }
        outputStream.flush();
        outputStream.close();
    }

    private static DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        documentBuilderFactory.setXIncludeAware(false);
        documentBuilderFactory.setExpandEntityReferences(false);

        return documentBuilderFactory.newDocumentBuilder();
    }

    private static org.w3c.dom.Document newDocument() throws ParserConfigurationException {
        return newDocumentBuilder().newDocument();
    }
    /**
     * XML格式字符串转换为Map
     *
     * @param strXML XML字符串
     * @return XML数据转换后的Map
     * @throws Exception
     */
    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        try {
            Map<String, String> data = new HashMap<String, String>();
            DocumentBuilder documentBuilder = newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception ex) {
                // do nothing
            }
            return data;
        } catch (Exception ex) {
            LOGGER.warn("Invalid XML, can not convert to map. Error message: {}. XML content: {}", ex.getMessage(), strXML);
            throw ex;
        }

    }

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param data Map类型数据
     * @return XML格式的字符串
     * @throws Exception
     */
    public static String mapToXml(Map<String, String> data) throws Exception {
        org.w3c.dom.Document document = newDocument();
        org.w3c.dom.Element root = document.createElement("xml");
        document.appendChild(root);
        for (String key: data.keySet()) {
            String value = data.get(key);
            if (value == null) {
                value = "";
            }
            value = value.trim();
            org.w3c.dom.Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
        try {
            writer.close();
        }
        catch (Exception ex) {
        }
        return output;
    }

    public static String getSystemDate(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 判断签名是否正确
     *
     * @param xmlStr XML格式数据
     * @param key API密钥
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(String xmlStr, String key) throws Exception {
        Map<String, String> data = xmlToMap(xmlStr);
        if (!data.containsKey(SIGN) ) {
            return false;
        }
        String sign = data.get(SIGN);
        return generateSignature(data, key).equals(sign);
    }

    /**
     * 生成签名
     *
     * @param data 待签名数据
     * @param key API密钥
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key) throws Exception {
        return generateSignature(data, key, SignType.MD5);
    }

    /**
     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
     *
     * @param data 待签名数据
     * @param key API密钥
     * @param signType 签名方式
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key, SignType signType) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(SIGN)) {
                continue;
            }
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append(key);
        if (SignType.MD5.equals(signType)) {
            return MD5(sb.toString()).toUpperCase();
        }
        else if (SignType.HMACSHA256.equals(signType)) {
            return HMACSHA256(sb.toString(), key);
        }
        else {
            throw new Exception(String.format("Invalid sign_type: %s", signType));
        }
    }

    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String MD5(String data) throws Exception {
        java.security.MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 生成 HMACSHA256
     * @param data 待处理数据
     * @param key 密钥
     * @return 加密结果
     * @throws Exception
     */
    public static String HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 微信产生20位随机字符串
     * @return
     */
    public static String getNonceStr(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<20;i++){
            int index = random.nextInt(NONE_STR.length());
            sb.append(NONE_STR.charAt(index));
        }
        return sb.toString();
    }
    /**
     * 微信支付时间戳
     * @return
     */
    public static String getCurrentTimeStamp(){
        //当前毫秒数除以1000
        Long timeStamp = System.currentTimeMillis();
        return String.valueOf(timeStamp/1000);
    }

    public static String createSign(StringBuilder params,String appKey){
        //删除&符号,拼接key,md5加密
        params.deleteCharAt(0);
        params.append("&key=").append(appKey);
        LOGGER.info("签名组装==>{}",params);
        String md5Str = MD5Util.MD5(params.toString());

        return md5Str;
    }

    public static String getXmlRequest(HttpServletRequest request) throws IOException{
        /** 读取接收到的xml消息 */
        StringBuffer sb = new StringBuffer();
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String s = "";
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        String xml = sb.toString(); //此为接收到微信端发送过来的xml数据
        return xml;
    }

    public static void main(String[] args){
        String orderNo = createOrderNo();
        System.out.println(orderNo);

    }

    public enum WeiXinStatus{
        TICKET_JSON_FAIL("-3","二维码解析json失败"),TICKET_FAIL("-2","生成ticket失败"), INVALID_TOKEN("-1","access_token失效");

        WeiXinStatus(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private String code;
        private String desc;

        public static String getValue(String code){
            for(WeiXinStatus wxs :WeiXinStatus.values()){
                if (StringUtils.equals(wxs.code,code)){
                    return wxs.desc;
                }
            }
            return null;
        }


        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum SignType{
        MD5, HMACSHA256
    }

    public enum WeiXinPublicNum{
        PAIBO, ICAR, TENANT
    }

    /**
     * 微信支付订单号生成
     * 17位订单号：yyyyMMddHHmmss + 3位随机数
     * @return
     */
    public static String createOrderNo(){
        lock.lock();
        String orderNo = "";
        StringBuffer sb = new StringBuffer();
        try {
            Random random = new Random();
            orderNo = getSystemDate()
                    .replace("-","")
                    .replace(":","")
                    .replace(" ","").trim();
            sb.append(orderNo);
            for (int i=0;i<3;i++){
                int index = random.nextInt(10);
                sb.append(index);
            }
        }finally {
            lock.unlock();
        }
        return sb.toString();
    }

    public static String gettimeFormart() {
        DateFormat format = new SimpleDateFormat("yyyyMMddssSSS");
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        sb.append(format.format(Calendar.getInstance().getTime()));
        for (int i=0;i<3;i++){
            int index = random.nextInt(10);
            sb.append(index);
        }
        return sb.toString();
    }

}
