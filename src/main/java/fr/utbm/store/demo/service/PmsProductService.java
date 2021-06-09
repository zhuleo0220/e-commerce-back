package fr.utbm.store.demo.service;


import fr.utbm.store.demo.model.PmsProduct;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface PmsProductService {
    /**
     * 创建商品
     */
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    int create(PmsProduct productParam);


    /**
     * 更新商品
     */
    @Transactional
    int update(Long id, PmsProduct productParam);


    PmsProduct getProduct(Long id);
    /**
     * 分页查询商品
     */
    List<PmsProduct> list(Long productCategoryId,Integer pageSize, Integer pageNum);


    List<PmsProduct> list(String keyword);

    List<PmsProduct> listAll(Long productCategoryId);

    @Transactional
    int delete(Long productId);
}
