package fr.utbm.store.demo.service.impl;


import fr.utbm.store.demo.dao.PmsProductCategoryDao;
import fr.utbm.store.demo.dao.PmsProductDao;
import fr.utbm.store.demo.model.PmsProduct;
import fr.utbm.store.demo.model.PmsProductCategory;
import fr.utbm.store.demo.model.PmsProductCategoryExample;
import fr.utbm.store.demo.model.PmsProductExample;
import fr.utbm.store.demo.service.PmsProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {
    @Autowired
    private PmsProductCategoryDao productCategoryMapper;
    @Autowired
    private PmsProductDao productMapper;


    @Override
    public int create(PmsProductCategory pmsProductCategoryParam) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setProductCount(0);
        BeanUtils.copyProperties(pmsProductCategoryParam, productCategory);
        int count = productCategoryMapper.insertSelective(productCategory);
        //创建筛选属性关联
        return count;
    }



    @Override
    public int update(Long id, PmsProductCategory pmsProductCategoryParam) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setId(id);
        BeanUtils.copyProperties(pmsProductCategoryParam, productCategory);
        //更新商品分类时要更新商品中的名称
        PmsProduct product = new PmsProduct();
        product.setProductCategoryName(productCategory.getName());
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andProductCategoryIdEqualTo(id);
        productMapper.updateByExampleSelective(product,example);

        return productCategoryMapper.updateByPrimaryKeySelective(productCategory);
    }

    @Override
    public List<PmsProductCategory> getList(Long parentId) {
        PmsProductCategoryExample example = new PmsProductCategoryExample();

        if (parentId==-1){
            return productCategoryMapper.selectByExample(example);
        }
        example.createCriteria().andParentIdEqualTo(parentId);
        return productCategoryMapper.selectByExample(example);
    }

    @Override
    public int delete(Long id) {
        return productCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PmsProductCategory getItem(Long id) {
        return productCategoryMapper.selectByPrimaryKey(id);
    }





}
