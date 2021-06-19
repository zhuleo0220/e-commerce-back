package fr.utbm.store.demo.controller;


import fr.utbm.store.demo.api.CommonResult;
import fr.utbm.store.demo.model.OmsCartItem;
import fr.utbm.store.demo.model.OmsOrder;
import fr.utbm.store.demo.model.UmsAdmin;
import fr.utbm.store.demo.service.OmsOrderService;
import fr.utbm.store.demo.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@Api(tags = "OmsOrderController", description = "订单管理")
@RequestMapping("/order")
public class OmsOrderController {
    @Autowired
    private OmsOrderService orderService;

    @Autowired
    private UmsAdminService umsAdminService;

    @ApiOperation("根据购物车信息生成确认单信息")
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createOrder() {
        orderService.createOrder();
        return CommonResult.success(1);
    }


    @ApiOperation("用户确认收货")
    @RequestMapping(value = "/confirmReceiveOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult confirmReceiveOrder(Long orderId) {
        orderService.confirmReceiveOrder(orderId);
        return CommonResult.success(null);
    }



    @ApiOperation("查询订单")
    @RequestMapping(value = "/list/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<OmsOrder>> list(@PathVariable Long orderId) {
        Long id=orderId;
        if(id==0){
            List<OmsOrder> orderList = orderService.list(id);
            return CommonResult.success(orderList);
        }else {
            List<OmsOrder> orderList = orderService.list(umsAdminService.getCurrentUser().getId());
            return CommonResult.success(orderList);
        }

    }

    @ApiOperation("批量发货")
    @RequestMapping(value = "/update/delivery", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delivery(@RequestBody Long id) {
        int count = orderService.delivery(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量关闭订单")
    @RequestMapping(value = "/update/close", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult close(@RequestParam("ids") List<Long> ids, @RequestParam String note) {
        int count = orderService.close(ids, note);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除订单")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") Long id) {
        int count = orderService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取订单详情：订单信息、商品信息、操作记录")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<OmsCartItem>> itemDetail(@PathVariable Long id) {
        List<OmsCartItem> orderDetailResult = orderService.itemDetail(id);
        return CommonResult.success(orderDetailResult);
    }





    @ApiOperation("备注订单")
    @RequestMapping(value = "/update/note", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateNote(@RequestParam("id") Long id,
                                   @RequestParam("note") String note,
                                   @RequestParam("status") Integer status) {
        int count = orderService.updateNote(id, note, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
