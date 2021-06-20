package fr.utbm.store.demo.service.impl;


import fr.utbm.store.demo.dao.AddressDao;
import fr.utbm.store.demo.model.Address;
import fr.utbm.store.demo.model.AddressExample;
import fr.utbm.store.demo.service.UmsMemberReceiveAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UmsMemberReceiveAddressServiceImpl implements UmsMemberReceiveAddressService {
    private static final Logger LOGGER = LoggerFactory.getLogger(fr.utbm.store.demo.service.impl.UmsMemberReceiveAddressServiceImpl.class);

    @Autowired
    private AddressDao addressDao;

    @Override
    public int update(Long id, Address address) {
        AddressExample addressExample=new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(id);
        List<Address> searchAddress= addressDao.selectByExample(addressExample);
        if (searchAddress.size()==0){
            address.setUserId(id);
            addressDao.insert(address);
            LOGGER.info("address created:"+address);
            return 1;
        } else {
            address.setId(searchAddress.get(0).getId());
            addressDao.updateByPrimaryKey(address);
            LOGGER.info("address updated:"+address);
            return 1;
        }
    }

    @Override
    public Address getAddress(Long id) {
        AddressExample addressExample=new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(id);
        List<Address> searchAddress= addressDao.selectByExample(addressExample);
        if (searchAddress.size()==0){

            return null;
        } else {

            return searchAddress.get(0);
        }
    }
}
