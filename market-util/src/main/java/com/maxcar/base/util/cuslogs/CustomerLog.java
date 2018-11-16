package com.maxcar.base.util.cuslogs;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.net.SyslogAppender;

public class CustomerLog {    
      
    /** 
     * 继承Level 
     * @author Sevencm 
     * 
     */  
    private static class CustomerLogLevel extends Level {
        public CustomerLogLevel(int level, String levelStr, int syslogEquivalent) {  
            super(level, levelStr, syslogEquivalent);  
        }         
    }  
      
    /** 
     * 自定义级别名称，以及级别范围 
     */  
    private static final Level CustomerLevel = new CustomerLogLevel(20050,"CUSTOMER", SyslogAppender.LOG_LOCAL0);
      
    /** 
     * 使用日志打印logger中的log方法 
     *  
     * @param logger 
     * @param objLogInfo 
     */  
    public static void customerLog(Logger logger, Object objLogInfo){
        logger.log(CustomerLevel, objLogInfo);  
    }  
      
      
      
}  