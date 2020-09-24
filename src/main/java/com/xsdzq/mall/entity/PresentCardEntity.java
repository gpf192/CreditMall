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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "mall_present_card")
@EntityListeners(AuditingEntityListener.class)
public class PresentCardEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * default entity id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_present_card")
	@SequenceGenerator(name = "sequence_present_card", sequenceName = "sequence_present_card", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@Column(name = "card_id", unique = true)
	private String cardId;

	@Column(name = "password")
	private String password;

	@Column(name = "card_status")
	private int cardStatus;

	@Column(name = "convert_status")
	private int convertStatus;


	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "present_id", referencedColumnName = "id")
	private PresentEntity presentEntity;

	@Column(name = "create_date")
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "convert_date")
	private Date convertDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(int cardStatus) {
		this.cardStatus = cardStatus;
	}

	public int getConvertStatus() {
		return convertStatus;
	}

	public void setConvertStatus(int convertStatus) {
		this.convertStatus = convertStatus;
	}

	public PresentEntity getPresentEntity() {
		return presentEntity;
	}

	public void setPresentEntity(PresentEntity presentEntity) {
		this.presentEntity = presentEntity;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getConvertDate() {
		return convertDate;
	}

	public void setConvertDate(Date convertDate) {
		this.convertDate = convertDate;
	}

	@Override
	public String toString() {
		return "PresentCardEntity [id=" + id + ", cardId=" + cardId + ", password=" + password + ", cardStatus="
				+ cardStatus + ", convertStatus=" + convertStatus + ", presentEntity=" + presentEntity + ", createDate="
				+ createDate + ", convertDate=" + convertDate + "]";
	}

}
