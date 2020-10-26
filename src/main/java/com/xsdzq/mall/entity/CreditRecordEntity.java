package com.xsdzq.mall.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mall_credit_record")
@EntityListeners(AuditingEntityListener.class)
public class CreditRecordEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_record_sequence")
	@SequenceGenerator(name = "credit_record_sequence", sequenceName = "sequence_credit_record", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "type", nullable = false)
	private boolean type; // type 为0 查找减少原因 着reason，type 查找 item itemCode

	@Column(name = "reason")
	private String reason;

	@Column(name = "reason_code")
	private String reasonCode;

	// 项目名称
	@Column(name = "item")
	private String item;

	// 项目代码
	@Column(name = "item_code")
	private String itemCode;

	// 积分数
	@Column(name = "integral_number")
	private int integralNumber;

	// 积分金额 这个字段保留，不需要
	@Column(name = "value")
	private double value;

	// 兑换类型，0，新增状态，未兑换，1.未完全兑换，2.已完成兑换 
	@Column(name = "change_type")
	private int changeType = 0;

	@Column(name = "remind_numer")
	private int remindNumer = 0;

	@Column(name = "data_flag")
	private String dateFlag; // 每日的判断标准

	@Column(name = "group_time")
	private String groupTime; // group by 计算 202009

	@Column(name = "begin_date")
	private String beginDate;// 生效日期

	@Column(name = "end_date")
	private int endDate;// 失效日期

	@Column(name = "record_time", nullable = false)
	private Date recordTime;

	// 修改时间
	@Column(name = "modifytime", nullable = true)
	@LastModifiedDate
	private Date modifytime;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	private MallUserEntity mallUserEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public int getIntegralNumber() {
		return integralNumber;
	}

	public void setIntegralNumber(int integralNumber) {
		this.integralNumber = integralNumber;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getDateFlag() {
		return dateFlag;
	}

	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}

	public int getChangeType() {
		return changeType;
	}

	public void setChangeType(int changeType) {
		this.changeType = changeType;
	}

	public int getRemindNumer() {
		return remindNumer;
	}

	public void setRemindNumer(int remindNumer) {
		this.remindNumer = remindNumer;
	}

	public String getGroupTime() {
		return groupTime;
	}

	public void setGroupTime(String groupTime) {
		this.groupTime = groupTime;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public int getEndDate() {
		return endDate;
	}

	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	@JsonIgnore
	public MallUserEntity getMallUserEntity() {
		return mallUserEntity;
	}

	public void setMallUserEntity(MallUserEntity mallUserEntity) {
		this.mallUserEntity = mallUserEntity;
	}

	@Override
	public String toString() {
		return "CreditRecordEntity [id=" + id + ", type=" + type + ", reason=" + reason + ", reasonCode=" + reasonCode
				+ ", item=" + item + ", itemCode=" + itemCode + ", integralNumber=" + integralNumber + ", value="
				+ value + ", changeType=" + changeType + ", remindNumer=" + remindNumer + ", dateFlag=" + dateFlag
				+ ", groupTime=" + groupTime + ", beginDate=" + beginDate + ", endDate=" + endDate + ", recordTime="
				+ recordTime + ", modifytime=" + modifytime + ", mallUserEntity=" + mallUserEntity + "]";
	}

}
