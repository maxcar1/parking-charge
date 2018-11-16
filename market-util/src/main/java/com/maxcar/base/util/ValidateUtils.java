package com.maxcar.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 相关属性验证工具
 *
 */
public class ValidateUtils {
	
	/**
	 * 正则表达式：验证只能包含数字，字母，下划线，中文，除此之外不行
	 */          
	public static final String REGEX_CHAR = "^[\u4e00-\u9fa5_a-zA-Z0-9]+$";
	
	/**
	 * 正则表达式：验证包含数字，字母，下划线，除此之外不行
	 */
	public static final String REGEX_NO = "^\\w{3,20}$"; 
	
    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z0-9]\\w{4,20}$";
    
    /**
     * 纳税人识别号
     */
    public static final String REGEX_TAXNO = "^(?!\\d+$|[a-zA-Z]+$)\\w{15,}$";
    /**
     * 暂时的VIN码校验 字母与数字组合17位
     */
    public static final String REGEX_VIN ="(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{17}";
    
    /**
     * 设备编号的校验  字母数字的组合12位
     */
    public static final String REGEX_EquipNo = "(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{12}";
    
    
    /**
     * 暂时的发动机编号 7-8 位字母与数字组合
     */
    public static final String REGEX_ENGINENO = "(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{7,8}";

    /**
     * 正则表达式：简单密码校验
     */
    public static final String REGEX_PASSWORD = "(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}";
 
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
    
    /**
     * 正则表达式: 验证电话号码 
     */
    public static final String REGEX_TEL = "^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$";
    
    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
 
    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
 
    /**
     * 正则表达式：验证身份证                                                                
     */
    public static final String REGEX_ID_CARD = "^(\\d{18}$|^\\d{17}(\\d|X|x))$";
    
    /**
     * 正则表达式:验证日期 yyyy-MM-dd HH:mm:ss 或  yyyy-MM-dd 格式
     */
	public static final String REGEX_DATE = "^(((20[0-3][0-9]-(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|(20[0-3][0-9]-(0[2469]|11)-(0[1-9]|[12][0-9]|30))) (20|21|22|23|[0-1][0-9]):[0-5][0-9]:[0-5][0-9])$";
	
	/**
	 * 正则表达式:验证姓名 2-5个汉字 或 2-10个字母
	 */
	public static final String REGEX_NAME = "^(([\u4e00-\u9fa5]{2,5})|([a-zA-Z]{2,10}))$";
    
	
	/**
	 * 正则表达式:验证姓名 2-8个汉字 或 2-10个字母
	 */
	public static final String REGEX_NAME1 = "^(([\u4e00-\u9fa5]{2,8})|([a-zA-Z]{2,10}))$";
	
	/**
	 * 对纯数字进行校验
	 */
	public static final String REGEX_NUMBER = "^[0-9]*$";
	
	
	/**
	 * 正则表达式: 常规车牌号校验
	 */
	public static final String REGEX_CARNO = "^[京,津,渝,沪,冀,晋,辽,吉,黑,苏,浙,皖,闽,赣,鲁,豫,鄂,湘,粤,琼,川,贵,云,陕,秦,甘,陇,青,台,蒙,桂,宁,新,藏,澳,军,海,航,警][A-Z][0-9,A-Z]{5}$";
	
	
	/**
	 * 正则表达式：校验是否含有小数点
	 */
	public static final String REGEX_DOUBLE =  "^([0-9]{1,}[.][0-9]*)$";
	
    /**
     * 校验用户名
     * 
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }
    
    /**
     * 校验设备的编号
     * @param equipNo
     * @return
     */
    public static boolean isEquipNo(String equipNo) {
        return Pattern.matches(REGEX_EquipNo, equipNo);
    }
    
    
    /**
     * 校验商铺名字
     * @param name
     * @return
     */
    
    public static boolean isName1(String name) {
        return Pattern.matches(REGEX_NAME1, name);
    }
    /**
     * 校验纳税人识别号
     * @param taxno
     * @return
     */
    public static boolean isTaxno(String taxno){
    	return Pattern.matches(REGEX_TAXNO, taxno);
    }
     
    
    /**
     * 校验密码
     * 
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }
    
    
    /**
     * 
     * @param number
     * @return
     */
    public static boolean isNumber(String number) {
        return Pattern.matches(REGEX_NUMBER, number);
    }
    
    /**
     * 校验手机号
     * 
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
    
    /**
     * 检验电话号码
     * @param tel
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isTel(String tel) {
        return Pattern.matches(REGEX_TEL, tel);
    }
 
    /**
     * 校验邮箱
     * 
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
 
    /**
     * 校验汉字
     * 
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }
 
    /**
     * 验证合同编号
     * @param no
     * @return
     */
    public static boolean isNo(String no) {
        return Pattern.matches(REGEX_NO, no);
    }
    
    /**
     * 校验身份证
     * 
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }
    
    /**
     * 校验日期格式
     * @param date
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isDate(String date) {
        return Pattern.matches(REGEX_DATE, date);
    }
    
    /**
     * 校验是否含有特殊字符
     * @param str
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChar(String str) {
        return Pattern.matches(REGEX_CHAR, str);
    }
    
    /**
     * 验证名称是否在2-5个汉字  2-10个字母之内
     * @param name
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isName(String name) {
        return Pattern.matches(REGEX_NAME, name);
    }
    
    /**
     * 判断是否是常规车牌
     * @param carNo
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isCarNo(String carNo) {
        return Pattern.matches(REGEX_CARNO, carNo);
    }
    /**
     * 判断VIN码是否由字母数字组合17位
     * @param vin
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isVin(String vin) {
        return Pattern.matches(REGEX_VIN, vin);
    }
    
    public static boolean isNumeric(String str){
    	return Pattern.matches(REGEX_DOUBLE, str);
    }
    
    
    /**
     * 判断发动机编号字母数字组合7-8位
     * @param engine
     * @return
     */
    public static boolean isEnginNo(String engine) {
        return Pattern.matches(REGEX_ENGINENO, engine);
    }
    
    /**
     * 常规密码校验   
     * 1) 密码控制只能输入字母、数字、特殊符号(~!@#$%^&*()_+[]{}|\;:'",./<>?)
	 * 2) 长度 6-16 位，必须包括字母、数字、特殊符号中的2种
	 * 符合上述规则返回true 否则 返回false
     */
    public static boolean isPwd(String pwd) {
    	//判断密码是否包含数字：包含返回1，不包含返回0
    	int i = pwd.matches(".*\\d+.*") ? 1 : 0;

    	//判断密码是否包含字母：包含返回1，不包含返回0
    	int j = pwd.matches(".*[a-zA-Z]+.*") ? 1 : 0;

    	//判断密码是否包含特殊符号(~!@#$%^&*()_+|<>,.?/:;'[]{}\)：包含返回1，不包含返回0
    	int k = pwd.matches(".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*") ? 1 : 0;

    	//判断密码长度是否在6-16位
    	int l = pwd.length();

    	if(i + j + k < 2 || l < 6 || l > 16){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    /**
     * 判断文件名是否符合图片结尾格式
     * @param filename
     * @return
     */
    public static boolean isPictureType(String filename){   
        String reg = ".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$";   
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(filename.toLowerCase());
        return matcher.find();
    }
    
    
    /**
     * 校验银行卡号
     * 1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。
	 * 2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，则将其减去9），再求和。
	 * 3、将奇数位总和加上偶数位总和，结果应该可以被10整除。
	 *   例如，卡号是：5432123456788881
	 *	   则奇数、偶数位（用红色标出）分布：5432123456788881
	 *	   奇数位和=35
	 *	   偶数位乘以2（有些要减去9）的结果：16 2 6 1 5 7 7，求和=35。
	 *	   最后35+35=70可以被10整除，认定校验通过。
     * @return
     */
    /*public static boolean isBankNo(String BankNo){
    	  int sumOdd = 0;
    	  int sumEven = 0;
    	  int length = BankNo.length();
    	  int[] wei = new int[length];
    	  for (int i = 0; i < BankNo.length(); i++) {
    	   wei[i] = Integer.parseInt(BankNo.substring(length - i - 1, length
    	     - i));// 从最末一位开始提取，每一位上的数值
    	  }
    	  for (int i = 0; i < length / 2; i++) {
    	   sumOdd += wei[2 * i];
    	   if ((wei[2 * i + 1] * 2) > 9)
    	    wei[2 * i + 1] = wei[2 * i + 1] * 2 - 9;
    	   else
    	    wei[2 * i + 1] *= 2;
    	   sumEven += wei[2 * i + 1];
    	  }
    	  if ((sumOdd + sumEven) % 10 == 0)
    	   return true;
    	  else
    	   return false;
    }
*/
    
}