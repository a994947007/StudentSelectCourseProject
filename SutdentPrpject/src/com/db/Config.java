package com.db;

import java.io.IOException;
import java.util.Properties;
/**
 * 获取系统配置
 * @author Lenovo
 *
 */
public class Config {
	private volatile static Config config = null;
	private Properties properties = null;
	private Config() {
	}
	static{
		if(config == null){
			synchronized (Config.class) {
				if(config == null){
					config = new Config();
					config.properties = new Properties();
					try {
						config.properties.load(Config.class.getClassLoader().getResourceAsStream("db.properties"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	

	public String getConfig(String name){
		return properties.getProperty(name);
	}
	
	public static Config getInstance(){
		return config;
	}
}
