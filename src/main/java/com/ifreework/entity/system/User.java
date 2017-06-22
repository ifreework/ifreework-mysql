package com.ifreework.entity.system;

import java.io.Serializable;
import java.util.Date;

import com.ifreework.entity.db.County;
import com.ifreework.entity.db.Municipality;
import com.ifreework.entity.db.Province;

public class User implements Serializable{
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = -880792284516769954L;
	//columns START
	private String userId;
	private String username;
	private String password;
	private String personName;
	private String rights;
	private String roleId;
	private String status;
	private String skin;
	private String email;
	private String userNo;
	private String phone;
	private String provinceId;
	private String municipalityId;
	private String countyId;
	private String deailAddress;
	private String imgPath;
	private String isOnline;
	private String deptId;
	private Date birthday;
	private String school;
	private String remarks;
	
	private Integer start ;
	private Integer pageSize;
	
	
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	private Province province;
	private Municipality municipality;
	private County county;
	//columns END
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getMunicipalityId() {
		return municipalityId;
	}
	public void setMunicipalityId(String municipalityId) {
		this.municipalityId = municipalityId;
	}
	public String getCountyId() {
		return countyId;
	}
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	public String getDeailAddress() {
		return deailAddress;
	}
	public void setDeailAddress(String deailAddress) {
		this.deailAddress = deailAddress;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public Municipality getMunicipality() {
		return municipality;
	}
	public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}
	public County getCounty() {
		return county;
	}
	public void setCounty(County county) {
		this.county = county;
	}

}


