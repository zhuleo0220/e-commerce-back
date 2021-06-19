package fr.utbm.store.demo.service;


import fr.utbm.store.demo.model.OmsCartItem;
import fr.utbm.store.demo.model.OmsOrder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


public interface OmsOrderService {
    /**
     * 订单查询
     */
    List<OmsOrder> list(Long id);

    /**
     * 批量发货
     */
    @Transactional
    int delivery(Long id);

    /**
     * 批量关闭订单
     */
    @Transactional
    int close(List<Long> ids, String note);

    /**
     * 批量删除订单
     */
    int delete(Long id);

    /**
     * 修改订单备注
     */
    @Transactional
    int updateNote(Long id, String note, Integer status);

    List<OmsCartItem> itemDetail(Long id);


    void createOrder();

    /**
     * 确认收货
     */
    void confirmReceiveOrder(Long orderId);

}
