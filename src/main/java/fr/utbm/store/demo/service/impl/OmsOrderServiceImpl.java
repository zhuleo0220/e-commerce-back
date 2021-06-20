package fr.utbm.store.demo.service.impl;

import fr.utbm.store.demo.dao.AddressDao;
import fr.utbm.store.demo.dao.OmsCartItemDao;
import fr.utbm.store.demo.dao.OmsOrderDao;
import fr.utbm.store.demo.model.OmsCartItem;
import fr.utbm.store.demo.model.OmsCartItemExample;
import fr.utbm.store.demo.model.OmsOrder;
import fr.utbm.store.demo.model.OmsOrderExample;
import fr.utbm.store.demo.service.OmsOrderService;
import fr.utbm.store.demo.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Service
public class OmsOrderServiceImpl implements OmsOrderService {
    @Autowired
    private OmsOrderDao orderMapper;
    @Autowired
    private OmsCartItemDao omsCartItemDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private UmsAdminService umsAdminService;

    @Override
    public List<OmsOrder> list(Long id) {
        if (id==0) {
            OmsOrderExample example = new OmsOrderExample();
            example.createCriteria();
            return orderMapper.selectByExample(example);
        }
        else
        {
            OmsOrderExample example = new OmsOrderExample();
            example.createCriteria().andMemberIdEqualTo(id);
            return orderMapper.selectByExample(example);
        }
    }



    @Override
    public int delivery(Long id) {
        OmsOrder order=new OmsOrder();
        order.setDeliveryTime(new Date());
        order.setStatus(2);
        order.setNote("delivered,waiting the usr to comfirm");
        order.setId(id);
        //批量发货
        int count = orderMapper.updateByPrimaryKeySelective(order);
        //添加操作记录

        return count;
    }

    @Override
    public int close(List<Long> ids, String note) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateNote(Long id, String note, Integer status) {
        return 0;
    }


    @Override
    public List<OmsCartItem> itemDetail(Long id) {
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andOrderIdEqualTo(id);
        return omsCartItemDao.selectByExample(example);
    }

    @Override
    public void createOrder() {
        OmsCartItemExample example1=new OmsCartItemExample();
        example1.createCriteria().andMemberIdEqualTo(umsAdminService.getCurrentUser().getId()).andDeleteStatusEqualTo(0);
        List<OmsCartItem> omsCartItems= omsCartItemDao.selectByExample(example1);
        List<Long> cartIds=new LinkedList<Long>();
        for(OmsCartItem omsCart : omsCartItems){
            cartIds.add(omsCart.getId());
        }
        OmsOrder omsOrder=new OmsOrder();
        omsOrder.setStatus(1);
        omsOrder.setNote("payed , waiting for the admin to delivery");
        omsOrder.setMemberId(umsAdminService.getCurrentUser().getId());
        omsOrder.setCreateTime(new Date());
        omsOrder.setPaymentTime(new Date());
        BigDecimal total=new BigDecimal(0);
        for(Long id : cartIds){
            total=total.add(omsCartItemDao.selectByPrimaryKey(id).getPrice().multiply(new BigDecimal(omsCartItemDao.selectByPrimaryKey(id).getQuantity())));
        }
        omsOrder.setTotalAmount(total);
        omsOrder.setConfirmStatus(0);
        orderMapper.insert(omsOrder);
        OmsCartItemExample example=new OmsCartItemExample();
        example.createCriteria().andIdIn(cartIds);
        OmsCartItem omsCartItem=new OmsCartItem();
        omsCartItem.setDeleteStatus(1);
        omsCartItem.setOrderId(omsOrder.getId());
        omsCartItemDao.updateByExampleSelective(omsCartItem,example);
    }

    @Override
    public int confirmReceiveOrder(Long orderId) {
        OmsOrder omsOrder=new OmsOrder();
        omsOrder.setId(orderId);
        omsOrder.setConfirmStatus(1);
        omsOrder.setStatus(4);
        omsOrder.setNote("received, order finish");
        omsOrder.setReceiveTime(new Date());
    int count=orderMapper.updateByPrimaryKeySelective(omsOrder);
    return count;
    }


}
