package com.xsdzq.mall.model;

import java.util.List;

public class AllProductRespDTO {
    private List<PresentCategorys> cardCoupon;
    private List<DirectRechargeRespDTO> directRecharge;

    public List<PresentCategorys> getCardCoupon() {
        return cardCoupon;
    }

    public void setCardCoupon(List<PresentCategorys> cardCoupon) {
        this.cardCoupon = cardCoupon;
    }

    public List<DirectRechargeRespDTO> getDirectRecharge() {
        return directRecharge;
    }

    public void setDirectRecharge(List<DirectRechargeRespDTO> directRecharge) {
        this.directRecharge = directRecharge;
    }
}
