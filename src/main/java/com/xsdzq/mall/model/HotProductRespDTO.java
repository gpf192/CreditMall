package com.xsdzq.mall.model;

import com.xsdzq.mall.entity.PresentEntity;

import java.util.List;

public class HotProductRespDTO {
    private List<PresentEntity> cardCoupon;
    private List<DirectRechargeRespDTO> directRecharge;

    public List<PresentEntity> getCardCoupon() {
        return cardCoupon;
    }

    public void setCardCoupon(List<PresentEntity> cardCoupon) {
        this.cardCoupon = cardCoupon;
    }

    public List<DirectRechargeRespDTO> getDirectRecharge() {
        return directRecharge;
    }

    public void setDirectRecharge(List<DirectRechargeRespDTO> directRecharge) {
        this.directRecharge = directRecharge;
    }
}
