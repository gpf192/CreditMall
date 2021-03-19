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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 用户黑名单实体类，限制用户登录
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "mall_user_blacklist")
@EntityListeners(AuditingEntityListener.class)
public class UserBlackListEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * default entity id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_user_blacklist")
	@SequenceGenerator(name = "sequence_user_blacklist", sequenceName = "sequence_user_blacklist", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@Column(name = "client_id", unique = true)
	private String clientId;

	@Column(name = "client_name")
	private String clientName;

	@Column(name = "department_desc")
	private String departmentDesc;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "create_date")
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	public long getId() {
		return id;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public String getDepartmentDesc() {
		return departmentDesc;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "UserBlackListEntity [id=" + id + ", clientId=" + clientId + ", clientName=" + clientName
				+ ", departmentDesc=" + departmentDesc + ", createdBy=" + createdBy + ", createDate=" + createDate
				+ "]";
	}

}
