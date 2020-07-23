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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "present_card_sequence")
	@SequenceGenerator(name = "present_card_sequence", sequenceName = "present_card_sequence", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@Column(name = "card_id")
	private String cardId;

	@Column(name = "password")
	private String password;

	@Column(name = "card_status")
	private String cardStatus;

	@Column(name = "convert_status")
	private String convertStatus;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "presentId", referencedColumnName = "id")
	private PresentEntity present;

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

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getConvertStatus() {
		return convertStatus;
	}

	public void setConvertStatus(String convertStatus) {
		this.convertStatus = convertStatus;
	}

	public PresentEntity getPresent() {
		return present;
	}

	public void setPresent(PresentEntity present) {
		this.present = present;
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
		return "PresentCard [id=" + id + ", cardId=" + cardId + ", password=" + password + ", cardStatus=" + cardStatus
				+ ", convertStatus=" + convertStatus + ", Present=" + present + ", createDate=" + createDate
				+ ", convertDate=" + convertDate + "]";
	}

}
