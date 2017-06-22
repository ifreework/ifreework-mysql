package com.ifreework.common.constant;

public enum UserConstant {
	USER_CACHE_NAME("sys-userCache"), //用户信息缓存空间
	USERNAME_KEY_PREFIX("username-"), //用户缓存key前缀
	 
	AUTH_CACHE_NAME("sys-authCache"), //权限缓存空间
	RESOURCE_PERMISSIONS_PREFIX("resource-permissions-"), //资源所需权限key前缀
	USER_PERMISSIONS_PREFIX("user-permissions-"), //用户拥有权限key前缀
	
	
	MENU_CACHE_NAME("sys-menuCache"), //菜单缓存空间
	USER_MENU_PREFIX("user-menu-");  //用户拥有菜单key前缀
	
	private String value;
	private UserConstant(String value){
		this.value = value;
	}
	
	public String toString(){
		return value;
	}
}
