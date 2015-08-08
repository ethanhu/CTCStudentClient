package ctc.util;

/**
* 实现对Java配置文件Properties的读取、写入与更新操作
*/
import java.io.*;
import java.util.Properties;

import ctc.data.LoginEntry;

public class SystemProperty {

    //属性文件的路径
    private static String path = System.getProperty("user.dir")+"/resources/config/";
        
    private static Properties props = new Properties();
     
    public SystemProperty(String fileName) {
        String filepath = path + fileName;	
		try {//从输入流inputStream中读取属性列表即键和元素对
			InputStream inputStream = new FileInputStream(filepath);//this.getClass().getResourceAsStream(filepath);//
			props.load(inputStream); 
		} catch (Exception e) {
			System.err.println("配置文件没找到:" + e);
			//System.exit(-1);
		}
	}
    
    public static void writeProperties(String fileName, LoginEntry entry) {       
        try {
            String filepath = path + fileName;
        	OutputStream fos = new FileOutputStream(filepath);
        	props.setProperty("USERNAME",entry.getUserName());
      	  	props.setProperty("PASSWORD",entry.getPassword());
      	  	props.setProperty("CTCSERVERIP",entry.getCtcServerIP());
    	  	props.setProperty("CTCSERVERPORT",entry.getCtcServerPort());
      	  
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "write value");
            
        } catch (IOException e) {
            System.err.println("写配置文件错!"+ e);
        }
    }
    
    
    /**
    * 读取属性文件中相应键的值
    * @param key 主键
    * @return String
    */
    public static String getKeyValue(String key) {
        return props.getProperty(key);
    }

  
}
