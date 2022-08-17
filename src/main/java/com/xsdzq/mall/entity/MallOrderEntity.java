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
@Table(name = "mall_order",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"order_no"}), @UniqueConstraint(columnNames = {"request_time","client_id"})},
        indexes = {@Index(columnList = "client_id"), @Index(columnList = "trade_date")})
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class MallOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mall_order_sequence")
    @SequenceGenerator(name = "mall_order_sequence", sequenceName = "mall_order_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    // 客户姓名
    @Column(name = "client_name")
    private String clientName;

    // 客户号
    @Column(name = "client_id", nullable = false)
    private String clientId;

    // 交易日期yyyyMMdd
    @Column(name = "trade_date", nullable = false)
    private Integer tradeDate;

    // 请求时间
    @Column(name = "request_time", nullable = false)
    private Long requestTime;

    // 营业部编码
    @Column(name = "department_code")
    private String departmentCode;

    // 营业部名称
    @Column(name = "department_name")
    private String departmentName;

    // 订单号
    @Column(name = "order_no", nullable = false)
    private String orderNo;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    // 商品类别主键
    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    // 商品类别id
    @Column(name = "goods_type_id", nullable = false)
    private String goodsTypeId;

    // 商品类别名称
    @Column(name = "goods_type_name")
    private String goodsTypeName;

    // 商品编码
    @Column(name = "goods_no", nullable = false)
    private String goodsNo;

    // 商品名称
    @Column(name = "goods_name")
    private String goodsName;

    // 商品数量
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    // 充值手机号
    @Column(name = "recharge_number")
    private String rechargeNumber;

    // 充值用户名
    @Column(name = "recharge_user_name")
    private String rechargeUserName;

    // 充值金额
    @Column(name = "recharge_amount", scale = 4, nullable = false)
    private BigDecimal rechargeAmount;

    // 扣款金额
    @Column(name = "consume_amount", scale = 4)
    private BigDecimal consumeAmount;

    // 使用积分
    @Column(name = "use_integral", nullable = false)
    private Integer useIntegral;

    // 订单状态（兑换状态） 0:初始状态 1:处理中 2:成功 3:失败
    @Column(name = "order_status", nullable = false)
    private Integer orderStatus;

    // 订单创建时间-橙券系统
    @Column(name = "start_time")
    private Date startTime;

    // 订单完成时间-橙券系统
    @Column(name = "end_time")
    private Date endTime;

    // 充值状态码-橙券系统 RECHARGE:充值中 SUCCESS:成功 FAILURE:失败
    @Column(name = "recharge_status")
    private String rechargeStatus;

    // 充值错误码-橙券系统
    @Column(name = "recharge_code")
    private Integer rechargeCode;

    // 充值错误码说明-橙券系统
    @Column(name = "recharge_message")
    private String rechargeMessage;

    // 调账类型 0:默认状态 1:扣除 2:退回
    @Column(name = "adjustment_type", nullable = false)
    private Integer adjustmentType;

    // 调账原因
    @Column(name = "adjustment_reason")
    private String adjustmentReason;

    // 操作人
    @Column(name = "operator")
    private String operator;

    // 备注
    @Column(name = "remark")
    private String remark;

    // 创建时间 (下单时间)
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Integer tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getRechargeNumber() {
        return rechargeNumber;
    }

    public void setRechargeNumber(String rechargeNumber) {
        this.rechargeNumber = rechargeNumber;
    }

    public String getRechargeUserName() {
        return rechargeUserName;
    }

    public void setRechargeUserName(String rechargeUserName) {
        this.rechargeUserName = rechargeUserName;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public BigDecimal getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(BigDecimal consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public Integer getUseIntegral() {
        return useIntegral;
    }

    public void setUseIntegral(Integer useIntegral) {
        this.useIntegral = useIntegral;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRechargeStatus() {
        return rechargeStatus;
    }

    public void setRechargeStatus(String rechargeStatus) {
        this.rechargeStatus = rechargeStatus;
    }

    public Integer getRechargeCode() {
        return rechargeCode;
    }

    public void setRechargeCode(Integer rechargeCode) {
        this.rechargeCode = rechargeCode;
    }

    public String getRechargeMessage() {
        return rechargeMessage;
    }

    public void setRechargeMessage(String rechargeMessage) {
        this.rechargeMessage = rechargeMessage;
    }

    public Integer getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(Integer adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getAdjustmentReason() {
        return adjustmentReason;
    }

    public void setAdjustmentReason(String adjustmentReason) {
        this.adjustmentReason = adjustmentReason;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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