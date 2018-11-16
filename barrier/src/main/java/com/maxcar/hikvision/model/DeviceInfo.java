package com.maxcar.hikvision.model;

import com.sun.jna.NativeLong;

public class DeviceInfo
{
	private String m_sDVRIP;
	private short m_wDVRPort;
	private String m_sUserName;
	private String m_sPassword;
	private NativeLong m_nUserID;
	HCNetSDKLinux.NET_DVR_DEVICEINFO_V30 m_lpDeviceInfo;
	
    NativeLong m_lAlarmHandle;//guading handle
    NativeLong m_lListenHandle;//listening handle
	
	public DeviceInfo(String sDVRIP, short wDVRPort, String sUserName, String sPassword, HCNetSDKLinux.NET_DVR_DEVICEINFO_V30 lpDeviceInfo, NativeLong nUserID)
	{
		m_sDVRIP=sDVRIP;
		m_wDVRPort=wDVRPort;
		m_sUserName=sUserName;
		m_sPassword=sPassword;
		m_nUserID=nUserID;
		m_lpDeviceInfo=lpDeviceInfo;
		m_lAlarmHandle=new NativeLong(-1);
		m_lListenHandle=new NativeLong(-1);
		
	}
	
	public void setNUserID(NativeLong nUserID)
	{
		m_nUserID=nUserID;
	}

	public String getIP()
	{
		return m_sDVRIP;
	}

	public short getPort()
	{
		return m_wDVRPort;
	}

	public String getUserName()
	{
		return m_sUserName;
	}

	public String getPassword()
	{
		return m_sPassword;
	}
		
	public NativeLong getNUserID()
	{
		return m_nUserID;
	}
	
	HCNetSDKLinux.NET_DVR_DEVICEINFO_V30 getlpDeviceInfo()
	{
		return m_lpDeviceInfo;
	}
	
	NativeLong getAlarmHandle()
	{
		return m_lAlarmHandle;
	}
	
	void setAlarmHandle(NativeLong lAlarm)
	{
		m_lAlarmHandle=lAlarm;
	}
	
	NativeLong getListenHandle()
	{
		return m_lListenHandle;
	}
	
	void setListenHandle(NativeLong lListen)
	{
		m_lListenHandle=lListen;
	}
	
	
	
	
	
}
