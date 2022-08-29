package com.xsdzq.mall.manager;

import com.xsdzq.mall.constants.OrderStatusEnum;
import com.xsdzq.mall.dao.CreditRecordRepository;
import com.xsdzq.mall.dao.MallUserInfoRepository;
import com.xsdzq.mall.dao.OrderRepository;
import com.xsdzq.mall.entity.CreditRecordEntity;
import com.xsdzq.mall.entity.MallOrderEntity;
import com.xsdzq.mall.entity.MallUserInfoEntity;
import com.xsdzq.mall.service.MallUserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Component
public class OrderManager {
    @Resource
    private MallUserInfoRepository mallUserInfoRepository;
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private CreditRecordRepository creditRecordRepository;
    @Resource
    private MallUserService mallUserService;

    @Transactional
    public void save(MallOrderEntity order, MallUserInfoEntity updateUserInfo) {
        int res = mallUserInfoRepository.addFrozenIntegral(updateUserInfo.getClientId(), updateUserInfo.getFrozenIntegral());
        if (res != 1) throw new RuntimeException("增加冻结积分失败");

        orderRepository.save(order);
    }

    @Transactional
    public void update(MallOrderEntity updateOrder, MallUserInfoEntity updateUserInfo, CreditRecordEntity creditRecord) {
        int res;
        if (OrderStatusEnum.SUCCESS.getCode().equals(updateOrder.getOrderStatus())) {
            res = mallUserInfoRepository.reduceFrozenIntegral(updateUserInfo.getClientId(), updateUserInfo.getFrozenIntegral());
            if (res != 1) throw new RuntimeException("减少冻结积分失败");

            res = mallUserInfoRepository.reduceUsableIntegral(updateUserInfo.getClientId(), updateUserInfo.getFrozenIntegral());
            if (res != 1) throw new RuntimeException("减少可用积分失败");

            // 增加积分兑换记录
            creditRecordRepository.save(creditRecord);
            // 设置原始积分已被使用
            mallUserService.handleRudeceCredit(creditRecord.getMallUserEntity(), updateUserInfo.getFrozenIntegral());
        } else if (OrderStatusEnum.FAILURE.getCode().equals(updateOrder.getOrderStatus())) {
            res = mallUserInfoRepository.reduceFrozenIntegral(updateUserInfo.getClientId(), updateUserInfo.getFrozenIntegral());
            if (res != 1) throw new RuntimeException("减少冻结积分失败");
        }

        orderRepository.save(updateOrder);
    }
}
