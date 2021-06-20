package fr.utbm.store.demo.controller;


import fr.utbm.store.demo.api.CommonResult;
import fr.utbm.store.demo.model.OmsCartItem;
import fr.utbm.store.demo.model.PmsProduct;
import fr.utbm.store.demo.service.OmsCartItemService;
import fr.utbm.store.demo.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@Controller
@Api(tags = "OmsCartItemController", description = "购物车管理")
@RequestMapping("/cart")
public class OmsCartItemController {
    @Autowired
    private OmsCartItemService cartItemService;
    @Autowired
    private UmsAdminService umsAdminService;

    @ApiOperation("添加商品到购物车")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestBody PmsProduct pmsProduct) {
        OmsCartItem cartItem=new OmsCartItem();
        cartItem.setMemberId(umsAdminService.getCurrentUser().getId());
        cartItem.setPrice(pmsProduct.getPrice());
        cartItem.setProductId(pmsProduct.getId());
        cartItem.setProductName(pmsProduct.getName());
        cartItem.setQuantity(1);
        cartItem.setSubtotal(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        int count = cartItemService.add(cartItem);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取某个会员的购物车列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<OmsCartItem>> list() {
        List<OmsCartItem> cartItemList = cartItemService.list(umsAdminService.getCurrentUser().getId());
        return CommonResult.success(cartItemList);
    }



    @ApiOperation("修改购物车中某个商品的数量")
    @RequestMapping(value = "/update/quantity/", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult updateQuantity( @RequestBody OmsCartItem omsCartItem) {

        int count = cartItemService.updateQuantity(omsCartItem.getId(), umsAdminService.getCurrentUser().getId(), omsCartItem.getQuantity());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }





    @ApiOperation("删除购物车中的某个商品")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult delete(Long id) {
        int count = cartItemService.delete(umsAdminService.getCurrentUser().getId(), id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("清空购物车")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult clear() {
        int count = cartItemService.clear(umsAdminService.getCurrentUser().getId());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
