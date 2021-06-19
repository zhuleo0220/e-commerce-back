package fr.utbm.store.demo.service.impl;

import com.github.pagehelper.PageHelper;

import fr.utbm.store.demo.dao.PmsProductMapper;
import fr.utbm.store.demo.model.PmsProduct;
import fr.utbm.store.demo.model.PmsProductExample;
import fr.utbm.store.demo.service.PmsProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class PmsProductServiceImpl implements PmsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(fr.utbm.store.demo.service.impl.PmsProductServiceImpl.class);

    @Autowired
    private PmsProductMapper pmsProductMapper;


    @Override
    public int create(PmsProduct productParam) {
        int count;
        //创建商品
        PmsProduct product = productParam;
        product.setId(null);
        pmsProductMapper.insertSelective(product);
        LOGGER.info("successful insert product, name:"+ product.getName());
        count = 1;
        return count;
    }




    @Override
    public int update(Long id, PmsProduct productParam) {
        int count;
        //更新商品信息
        PmsProduct product = productParam;
        product.setId(id);
        pmsProductMapper.updateByPrimaryKeySelective(product);
        count = 1;
        return count;
    }

    @Override
    public PmsProduct getProduct(Long id) {
        PmsProductExample productExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andIdEqualTo(id);
        return pmsProductMapper.selectByExampleWithBLOBs(productExample).get(0);
    }


    @Override
    public List<PmsProduct> list(Long productCategoryId,Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductExample productExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = productExample.createCriteria();
        if(productCategoryId!=null) {
            criteria.andProductCategoryIdEqualTo(productCategoryId);
        }
        return pmsProductMapper.selectByExampleWithBLOBs(productExample);
    }




    @Override
    public List<PmsProduct> list(String keyword) {
        PmsProductExample productExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = productExample.createCriteria();

        if(!StringUtils.isEmpty(keyword)){
            criteria.andNameLike("%" + keyword + "%");
           }
        return pmsProductMapper.selectByExampleWithBLOBs(productExample);
    }

    @Override
    public List<PmsProduct> listAll(Long productCategoryId) {
        PmsProductExample productExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = productExample.createCriteria();
        if(productCategoryId!=null&&productCategoryId!=0) {
            criteria.andProductCategoryIdEqualTo(productCategoryId);
        }
        return pmsProductMapper.selectByExampleWithBLOBs(productExample);
    }

    @Override
    public int delete(Long productId) {
        int count;
        //更新商品信息
        pmsProductMapper.deleteByPrimaryKey(productId);
        count = 1;
        return count;
    }


}
