package com.xsdzq.mall.model;

import java.math.BigDecimal;
import java.util.List;

public class DirectRechargeRespDTO {
    private String goodsTypeId;
    private String goodsTypeName;
    private String image;
    private Integer availableIntegral;
    private Integer priceDayQuota;
    private List<GoodsRespDTO> goodsList;

    public static class GoodsRespDTO {
        private String goodsNo;
        private String goodsName;
        private String smallImage;
        private String bigImage;
        private BigDecimal officialPrice;
        private Integer goodsIntegral;

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

        public String getSmallImage() {
            return smallImage;
        }

        public void setSmallImage(String smallImage) {
            this.smallImage = smallImage;
        }

        public String getBigImage() {
            return bigImage;
        }

        public void setBigImage(String bigImage) {
            this.bigImage = bigImage;
        }

        public BigDecimal getOfficialPrice() {
            return officialPrice;
        }

        public void setOfficialPrice(BigDecimal officialPrice) {
            this.officialPrice = officialPrice;
        }

        public Integer getGoodsIntegral() {
            return goodsIntegral;
        }

        public void setGoodsIntegral(Integer goodsIntegral) {
            this.goodsIntegral = goodsIntegral;
        }
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getAvailableIntegral() {
        return availableIntegral;
    }

    public void setAvailableIntegral(Integer availableIntegral) {
        this.availableIntegral = availableIntegral;
    }

    public Integer getPriceDayQuota() {
        return priceDayQuota;
    }

    public void setPriceDayQuota(Integer priceDayQuota) {
        this.priceDayQuota = priceDayQuota;
    }

    public List<GoodsRespDTO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsRespDTO> goodsList) {
        this.goodsList = goodsList;
    }
}
