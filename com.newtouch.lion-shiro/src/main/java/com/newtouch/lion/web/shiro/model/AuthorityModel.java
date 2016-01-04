/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RolePermissionModel.java 9552 2015年3月3日 下午6:09:31 WangLijun$
*/
package com.newtouch.lion.web.shiro.model; 

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;


/**
 * <p>
 * Title: 角色和权限模型
 * </p>
 * <p>
 * Description:  角色和权限模型
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class AuthorityModel  implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -2630273443875203310L;
	/**资源ID*/
	private Long id;
	/**URL*/
	private String url;
	/**角色名称*/
	private Set<String> roles;
	/**权限名称*/
	private Set<String> permissions;
	
	public AuthorityModel() {
		super();
		roles=new HashSet<String>();
		permissions=new HashSet<String>();
	}
	
	

	/**
	 * @return URL
	 */
	public String getUrl() {
		return url;
	}



	/**
	 * @param  URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the roles
	 */
	public String getRoles() {		
		if(!CollectionUtils.isEmpty(this.roles)){
			String role=this.roles.toString();
			return role.substring(1,role.length()-1);
		}
		return StringUtils.EMPTY;
	}
	/***
	 * 添加角色
	 * @param role 角色
	 */
	public  void addRole(String role){
		if(this.roles==null){
			this.roles=new HashSet<String>();
		}
		this.roles.add(role);
	}
	/***
	 * 添加权限
	 * @param permission 权限
	 */
	public void addPermission(String permission){
		if(this.permissions==null){
			this.permissions=new HashSet<String>();
		}
		this.permissions.add(permission);
	}
	
	
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	/**
	 * @return the permissions
	 */
	public String getPermissions() {
		
		if(!CollectionUtils.isEmpty(this.permissions)){
			String permission=permissions.toString();
			return permission.substring(1,permission.length()-1);
		}
		return StringUtils.EMPTY;
	}
	
	
	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}
	
	public static void main(String[] args) {
		Set<String> roles=new HashSet<String>();
		roles.add("name");
		roles.add("name2");
		System.out.println(roles.toString().substring(1, roles.toString().length()-2));
	}
	
}

	