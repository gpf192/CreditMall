package com.xsdzq.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "mall_product",
        uniqueConstraints = @UniqueConstraint(columnNames = {"goods_no", "goods_type_id"}))
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class MallProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mall_product_sequence")
    @SequenceGenerator(name = "mall_product_sequence", sequenceName = "mall_product_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    // 商品名称
    @Column(name = "goods_name")
    private String goodsName;

    // 商品编码
    @Column(name = "goods_no", nullable = false)
    private String goodsNo;

    // 商品面额
    @Column(name = "official_price", nullable = false)
    private BigDecimal officialPrice;

    // 实际价格
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    // 商品状态 0：上架，1：下架
    @Column(name = "sell_status", nullable = false)
    private Integer sellStatus;

    // 大图
    @Column(name = "big_image")
    private String bigImage;

    // 小图
    @Column(name = "small_image")
    private String smallImage;

    // 商品类别主键
    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    // 商品类别id
    @Column(name = "goods_type_id", nullable = false)
    private String goodsTypeId;

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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public BigDecimal getOfficialPrice() {
        return officialPrice;
    }

    public void setOfficialPrice(BigDecimal officialPrice) {
        this.officialPrice = officialPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(Integer sellStatus) {
        this.sellStatus = sellStatus;
    }

    public String getBigImage() {
        return bigImage;
    }

    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(String goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
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
