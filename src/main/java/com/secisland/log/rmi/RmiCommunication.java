package com.secisland.log.rmi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * RMI通信客户端类(实现RMIInterface接口)
 * @author zhulin
 *
 */
public class RmiCommunication {
	private static Logger logger = LoggerFactory.getLogger(RmiCommunication.class);
	private static Properties properties;
	private static String rmiIpPort;

	public RmiCommunication(){
		this.loadProperties();
		RmiCommunication.rmiIpPort = properties.getProperty("rmiIpPort");
	}
	
	public void findHost(final String ip){
		Thread t = new Thread(new Runnable(){  
            public void run(){ 
        		try {
        			RmiInterface rmiInterface = getInterface();
        			if ( rmiInterface == null ){
        				return;
        			}
        			rmiInterface.findHost(ip);
        		} catch (RemoteException e) {
        			logger.error("getSysStatus RemoteException:"+e.getMessage());
        		}
            }
        },"findHost");  
        t.start();  
	}
	
	public boolean isFindHost(){
		try {
			RmiInterface rmiInterface = getInterface();
			if ( rmiInterface == null ){
				return false;
			}
			return rmiInterface.isFindHost();
		} catch (RemoteException e) {
			logger.error("getSysStatus RemoteException:"+e.getMessage());
		}
		return false;
	}
	
	//做了负载处理，随机取第一个，第一个不成功后，然后连接第二个。
	private RmiInterface getInterface(){

		RmiInterface rmiInterface = null;
		try {
			rmiInterface = (RmiInterface)Naming.lookup(rmiIpPort+"/rmiService");	
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException:"+e.getMessage());
		} catch (RemoteException e) {
			logger.error("RemoteException:"+e.getMessage());
		} catch (NotBoundException e) {
			logger.error("NotBoundException:"+e.getMessage());
		}
		return rmiInterface;
	}
	

	// 加载配置文件
	private void loadProperties() {
		String path = Thread.currentThread().getContextClassLoader().getResource("application.properties").getPath();
		try {
			properties = new Properties();
			properties.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
