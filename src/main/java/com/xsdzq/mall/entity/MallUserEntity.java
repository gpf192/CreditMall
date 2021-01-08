package com.xsdzq.mall.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mall_user")
@EntityListeners(AuditingEntityListener.class)
public class MallUserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mall_user_sequence")
	@SequenceGenerator(name = "mall_user_sequence", sequenceName = "sequence_mall_user", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	// 客户号
	@Column(name = "client_id", unique = true, nullable = false, length = 100)
	private String clientId;

	// 登录ClientId
	@Column(name = "login_client_id", nullable = true, length = 100)
	private String loginClentId;

	@Column(name = "client_name", nullable = true, length = 300)
	private String clientName;

	// 资金账号
	@Column(name = "fund_account", unique = true, length = 100)
	private String fundAccount;

	@Column(name = "access_token", nullable = true, length = 100)
	private String accessToken;

	@Column(name = "password", nullable = true, length = 500)
	private String password;

	@Column(name = "mobile", nullable = true, length = 12)
	private String mobile;

	@Column(name = "app_version", nullable = true, length = 100)
	private String appVersion;

	@Column(name = "last_op_ip", nullable = true, length = 1000)
	private String lastOpIP;

	@Column(name = "last_login_time", nullable = true, length = 500)
	private String lastLoginTime;

	@Column(name = "department_code", length = 50)
	private String departmentCode;

	@Column(name = "department_name", length = 500)
	private String departmentName;

	// 创建时间
	@Column(name = "createtime")
	@CreatedDate
	private Date createtime;

	// 修改时间
	@Column(name = "modifytime", nullable = true)
	@LastModifiedDate
	private Date modifytime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@JsonIgnore
	public String getLoginClentId() {
		return loginClentId;
	}

	public void setLoginClentId(String loginClentId) {
		this.loginClentId = loginClentId;
	}

	@JsonIgnore
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@JsonIgnore
	public String getFundAccount() {
		return fundAccount;
	}

	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
	}

	@JsonIgnore
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@JsonIgnore
	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	@JsonIgnore
	public String getLastOpIP() {
		return lastOpIP;
	}

	public void setLastOpIP(String lastOpIP) {
		this.lastOpIP = lastOpIP;
	}

	@JsonIgnore
	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	@Override
	public String toString() {
		return "MallUserEntity [id=" + id + ", clientId=" + clientId + ", loginClentId=" + loginClentId
				+ ", clientName=" + clientName + ", fundAccount=" + fundAccount + ", accessToken=" + accessToken
				+ ", password=" + password + ", mobile=" + mobile + ", appVersion=" + appVersion + ", lastOpIP="
				+ lastOpIP + ", lastLoginTime=" + lastLoginTime + ", departmentCode=" + departmentCode
				+ ", departmentName=" + departmentName + ", createtime=" + createtime + ", modifytime=" + modifytime
				+ "]";
	}

}
