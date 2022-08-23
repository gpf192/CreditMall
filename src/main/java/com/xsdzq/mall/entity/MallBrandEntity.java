package com.xsdzq.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mall_brand",
        uniqueConstraints = @UniqueConstraint(columnNames = {"goods_type_id"}))
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
public class MallBrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mall_brand_sequence")
    @SequenceGenerator(name = "mall_brand_sequence", sequenceName = "mall_brand_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    // 商品类别id
    @Column(name = "goods_type_id", nullable = false)
    private String goodsTypeId;

    // 商品类别名称
    @Column(name = "goods_type_name")
    private String goodsTypeName;

    // 图像url
    @Column(name = "image_url")
    private String imageUrl;

    // 商品类别状态 0：上架，1：下架
    @Column(name = "sell_status", nullable = false)
    private Integer sellStatus;

    // 创建时间
    @Column(name = "create_time")
    @CreatedDate
    private Date createTime;

    // 更新时间
    @Column(name = "update_time")
    @LastModifiedDate
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(String goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(Integer sellStatus) {
        this.sellStatus = sellStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
