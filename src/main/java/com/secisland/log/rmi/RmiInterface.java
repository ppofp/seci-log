package com.secisland.log.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiInterface  extends Remote{
	
	//查询主机
	public void findHost(String ip) throws RemoteException;
	//查询主机状态
	public boolean isFindHost() throws RemoteException;
}
