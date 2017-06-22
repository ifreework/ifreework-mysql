package com.ifreework.util;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Properties;

import com.sun.management.OperatingSystemMXBean;

@SuppressWarnings("restriction")
public class WindowsInfoUtil {

	/**
	 * 描述：获取系统内存使用比例，百分比
	 * @return 
	 */
	public static double getMemeryScale() {
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

		// 总的物理内存+虚拟内存
		long totalvirtualMemory = osmxb.getTotalPhysicalMemorySize();

		// 剩余的物理内存
		long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();

		Double compare = (Double) (1 - freePhysicalMemorySize * 1.0 / totalvirtualMemory) * 100;
		return compare.intValue();
	}

	// 获取MAC地址的方法
	public static String getMACAddress() {
		InetAddress inetAddress;
		StringBuffer sb = new StringBuffer();
		try {
			inetAddress = InetAddress.getLocalHost();

			// 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
			byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();

			for (int i = 0; i < mac.length; i++) {
				if (i != 0) {
					sb.append("-");
				}
				String s = Integer.toHexString(mac[i] & 0xFF);
				sb.append(s.length() == 1 ? 0 + s : s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString().toUpperCase();
	}
	
	
	public static String getServerName(){
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			return "";
		}
		String hostName = addr.getHostName().toString(); // 获取本机计算机名称
		return hostName;
	}

	public static void main(String[] args) {
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

		Object s1 = osmxb.getArch();// 系统架构
		int s2 = osmxb.getAvailableProcessors();// CPU数量
		long s3 = osmxb.getCommittedVirtualMemorySize();// 虚拟内存大小
		long s4 = osmxb.getFreePhysicalMemorySize();// 空闲物理内存大小
		long s5 = osmxb.getFreeSwapSpaceSize();// 空闲磁盘大小
		Object s6 = osmxb.getName();// 操作系统名称
		Object s7 = osmxb.getObjectName();// 进程cpu负载
		double s8 = osmxb.getProcessCpuLoad();// 进程cpu时间
		double s9 = osmxb.getProcessCpuTime();// 进程cpu负载
		double s10 = osmxb.getSystemCpuLoad();// 系统CPU负载
		double s11 = osmxb.getSystemLoadAverage(); // 系统平均负载
		long s12 = osmxb.getTotalPhysicalMemorySize(); // 物理内存大小
		Object s13 = osmxb.getTotalSwapSpaceSize();// 硬盘大小
		Object s14 = osmxb.getVersion();// 版本

		System.out.println("系统架构" + s1); // 系统架构
		System.out.println("CPU数量:" + s2);// 系统架构
		System.out.println("虚拟内存大小:" + s3); // 虚拟内存大小
		System.out.println("空闲物理内存大小:" + s4); // 空闲物理内存大小
		System.out.println("空闲磁盘大小:" + s5); // 空闲磁盘大小
		System.out.println("操作系统名称:" + s6); // 操作系统名称
		System.out.println("ObjectName:" + s7); //
		System.out.println("进程cpu负载:" + s8); // 进程cpu负载
		System.out.println("进程cpu时间:" + s9); // 进程cpu时间
		System.out.println("系统CPU负载:" + s10); // 系统CPU负载
		System.out.println("系统平均负载:" + s11); // 系统平均负载
		System.out.println("物理内存大小:" + s12); // 物理内存大小
		System.out.println("硬盘大小:" + s13); // 硬盘大小
		System.out.println(" 版本:" + s14); // 版本

		System.out.println("内存使用比例:" + (1 - s4 * 1.0 / (s12))); // 版本
		System.out.println("内存使用比例:" + getMemeryScale()); // 版本

		try {
			InetAddress addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString(); // 获取本机ip
			String hostName = addr.getHostName().toString(); // 获取本机计算机名称
			System.out.println("本机IP：" + ip + "\n本机名称:" + hostName);
			Properties props = System.getProperties();
			System.out.println("操作系统的名称：" + props.getProperty("os.name"));
			System.out.println("操作系统的版本：" + props.getProperty("os.version"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
