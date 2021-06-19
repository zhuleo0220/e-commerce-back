package fr.utbm.store.demo.controller;


import fr.utbm.store.demo.api.CommonPage;
import fr.utbm.store.demo.api.CommonResult;
import fr.utbm.store.demo.exception.Asserts;
import fr.utbm.store.demo.model.PmsProduct;
import fr.utbm.store.demo.model.PmsProductParam;
import fr.utbm.store.demo.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;


@Controller
@Api(tags = "PmsProductController", description = "商品管理")
@RequestMapping("/product")
public class PmsProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(fr.utbm.store.demo.controller.PmsProductController.class);

    @Autowired
    private PmsProductService productService;

    @ApiOperation("创建商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestParam(name ="images", required = false) MultipartFile image,
                               @RequestParam(name = "description") String description,
                               @RequestParam(name = "price") BigDecimal price,
                               @RequestParam(name = "name") String productname,
                               @RequestParam(name = "stock") Integer stock,
                                @RequestParam(name = "keyword") String keyword,
                               @RequestParam(name = "categoryId") Long categoryId) {
        PmsProduct pmsProduct=new PmsProduct();
        pmsProduct.setDescription(description);
        pmsProduct.setName(productname);
        pmsProduct.setKeywords(keyword);
        pmsProduct.setPrice(price);
        pmsProduct.setStock(stock);
        pmsProduct.setProductCategoryId(categoryId);
       try
       {
        if (image != null) {
            pmsProduct.setImages(image.getBytes());
        }
    } catch (Exception e) {
           LOGGER.info("Unable to save product details, please try again");
    }
        int count = productService.create(pmsProduct);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据商品id获取商品编辑信息")
    @RequestMapping(value = "/updateInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsProduct> getUpdateInfo(@PathVariable Long id) {
        PmsProduct productResult = productService.getProduct(id);
        return CommonResult.success(productResult);
    }

    @ApiOperation("更新商品")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult update(@RequestParam(name ="images", required = false) MultipartFile image,
                               @RequestParam(name = "description") String description,
                               @RequestParam(name = "price") BigDecimal price,
                               @RequestParam(name = "name") String productname,
                               @RequestParam(name = "stock") Integer stock,
                               @RequestParam(name = "keyword") String keyword,
                               @RequestParam(name = "categoryId") Long categoryId,
                               @RequestParam(name = "productId") Long Id) {
        PmsProduct pmsProduct=new PmsProduct();
        pmsProduct.setDescription(description);
        pmsProduct.setName(productname);
        pmsProduct.setKeywords(keyword);
        pmsProduct.setPrice(price);
        pmsProduct.setStock(stock);
        pmsProduct.setProductCategoryId(categoryId);
        try
        {
            if (image != null) {
                pmsProduct.setImages(image.getBytes());
            }
        } catch (Exception e) {
            LOGGER.info("Unable to save product details, please try again");
        }
        int count = productService.update(Id, pmsProduct);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
    @ApiOperation("删除商品")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult deleteProduct(Long productId) {
        int count = productService.delete(productId);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询商品")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProduct>> getList(Long productCategoryId,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProduct> productList = productService.list(productCategoryId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productList));
    }
    @ApiOperation("查询商品")
    @RequestMapping(value = "/listAll/{productCategoryId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProduct>> getAllList(@PathVariable Long productCategoryId)
                                                         {
        List<PmsProduct> productList = productService.listAll(productCategoryId);
        return CommonResult.success(productList);
    }

    @ApiOperation("根据商品名称或货号模糊查询")
    @RequestMapping(value = "/simpleList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProduct>> getList(String keyword) {
        List<PmsProduct> productList = productService.list(keyword);
        return CommonResult.success(productList);
    }


}
