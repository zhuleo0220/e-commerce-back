package fr.utbm.store.demo.service;


import fr.utbm.store.demo.model.MemberCollection;
import fr.utbm.store.demo.model.PmsProduct;

import java.util.List;


public interface MemberCollectionService {
    /**
     * 添加收藏
     */
    int add(PmsProduct pmsProduct);

    /**
     * 删除收藏
     */
    int delete(Long productId);

    /**
     * 分页查询收藏
     */
    List<PmsProduct> list();


    /**
     * 清空收藏
     */
    void clear();
}
