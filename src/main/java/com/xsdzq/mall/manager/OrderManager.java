package com.xsdzq.mall.manager;

import com.xsdzq.mall.constants.OrderStatusEnum;
import com.xsdzq.mall.dao.MallUserInfoRepository;
import com.xsdzq.mall.dao.OrderRepository;
import com.xsdzq.mall.entity.MallOrderEntity;
import com.xsdzq.mall.entity.MallUserInfoEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Component
public class OrderManager {
    @Resource
    private MallUserInfoRepository mallUserInfoRepository;
    @Resource
    private OrderRepository orderRepository;

    @Transactional
    public void save(MallOrderEntity order, MallUserInfoEntity updateUserInfo) {
        int res = mallUserInfoRepository.addFrozenIntegral(updateUserInfo.getClientId(), updateUserInfo.getFrozenIntegral());
        if (res != 1) throw new RuntimeException("增加冻结积分失败");

        orderRepository.save(order);
    }

    @Transactional
    public void update(MallOrderEntity updateOrder, MallUserInfoEntity updateUserInfo) {
        int res;
        if (OrderStatusEnum.SUCCESS.getCode().equals(updateOrder.getOrderStatus())) {
            res = mallUserInfoRepository.reduceFrozenIntegral(updateUserInfo.getClientId(), updateUserInfo.getFrozenIntegral());
            if (res != 1) throw new RuntimeException("减少冻结积分失败");

            res = mallUserInfoRepository.reduceUsableIntegral(updateUserInfo.getClientId(), updateUserInfo.getFrozenIntegral());
            if (res != 1) throw new RuntimeException("减少可用积分失败");
        } else if (OrderStatusEnum.FAILURE.getCode().equals(updateOrder.getOrderStatus())) {
            res = mallUserInfoRepository.reduceFrozenIntegral(updateUserInfo.getClientId(), updateUserInfo.getFrozenIntegral());
            if (res != 1) throw new RuntimeException("减少冻结积分失败");

            res = mallUserInfoRepository.addUsableIntegral(updateUserInfo.getClientId(), updateUserInfo.getFrozenIntegral());
            if (res != 1) throw new RuntimeException("增加可用积分失败");
        }

        orderRepository.save(updateOrder);
    }
}
