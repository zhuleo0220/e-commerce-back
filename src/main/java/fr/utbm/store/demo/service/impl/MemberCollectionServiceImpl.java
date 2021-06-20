package fr.utbm.store.demo.service.impl;


import fr.utbm.store.demo.dao.MemberCollectionDao;
import fr.utbm.store.demo.dao.PmsProductDao;
import fr.utbm.store.demo.model.*;
import fr.utbm.store.demo.service.MemberCollectionService;
import fr.utbm.store.demo.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;


@Service
public class MemberCollectionServiceImpl implements MemberCollectionService {
    @Autowired
    private MemberCollectionDao memberCollectionDao;
    @Autowired
    private UmsAdminService memberService;
    @Autowired
    private PmsProductDao pmsProductDao;

    @Override
    public int add(PmsProduct pmsProduct) {
        int count = 0;
        UmsAdmin member = memberService.getCurrentUser();
        MemberCollection productCollection=new MemberCollection();
        productCollection.setMemberId(member.getId());
        productCollection.setProductId(pmsProduct.getId());
        MemberCollectionExample example=new MemberCollectionExample();
        example.createCriteria().andMemberIdEqualTo(productCollection.getMemberId()).andProductIdEqualTo(productCollection.getProductId());
        List<MemberCollection> findCollectionList = memberCollectionDao.selectByExample(example);
        MemberCollection findCollection=new MemberCollection();
        if (!CollectionUtils.isEmpty(findCollectionList)) {
            findCollection=findCollectionList.get(0);
            return count;
        }

            memberCollectionDao.insert(productCollection);
            count = 1;

        return count;
    }

    @Override
    public int delete(Long productId) {
        UmsAdmin member = memberService.getCurrentUser();
        MemberCollectionExample example=new MemberCollectionExample();
        example.createCriteria().andProductIdEqualTo(productId).andMemberIdEqualTo(member.getId());

        return memberCollectionDao.deleteByExample(example);
    }

    @Override
    public List<PmsProduct> list() {
        UmsAdmin member = memberService.getCurrentUser();
        MemberCollectionExample example= new MemberCollectionExample();
        example.createCriteria().andMemberIdEqualTo(member.getId());
        List<MemberCollection> memberCollectionList=memberCollectionDao.selectByExample(example);
        if (!CollectionUtils.isEmpty(memberCollectionList)) {
            List<Long> ids=new LinkedList<>();
            for(MemberCollection collection : memberCollectionList){
                ids.add(collection.getProductId());
            }
            PmsProductExample pmsProductExample=new PmsProductExample();
            pmsProductExample.createCriteria().andIdIn(ids);
            return pmsProductDao.selectByExampleWithBLOBs(pmsProductExample);
        }

        return null;
    }


    @Override
    public void clear() {
        UmsAdmin member = memberService.getCurrentUser();
        MemberCollectionExample example= new MemberCollectionExample();
        example.createCriteria().andMemberIdEqualTo(member.getId());
        memberCollectionDao.deleteByExample(example);
    }
}
