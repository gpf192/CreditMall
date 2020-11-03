package com.xsdzq.mall.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "mall_crm_credit_product_view")
@EntityListeners(AuditingEntityListener.class)
public class CRMCreditProductViewEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	
	@Column(name = "cpdm")
	private String productCode;//产品代码
	
	@Column(name = "cpmc")
	private String productName;//产品代码
	
	@Column(name = "jjlx")
	private String fundType;//基金类型
	
	@Column(name = "fxlx")
	private String riskType;//风险类型
	
	@Column(name = "cplx")
	private String productType;//产品类型
	
	@Column(name = "sgf")
	private String purchaseRate;//申购费率

	public String getProductCode() {
		return productCode;
	}

	public String getProductName() {
		return productName;
	}

	public String getFundType() {
		return fundType;
	}

	public String getRiskType() {
		return riskType;
	}

	public String getProductType() {
		return productType;
	}

	public String getPurchaseRate() {
		return purchaseRate;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public void setPurchaseRate(String purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	@Override
	public String toString() {
		return "CRMCreditProductViewEntity [productCode=" + productCode + ", productName=" + productName + ", fundType="
				+ fundType + ", riskType=" + riskType + ", productType=" + productType + ", purchaseRate="
				+ purchaseRate + "]";
	}
	
	
	
}
