package fr.utbm.store.demo.service;


import fr.utbm.store.demo.model.Address;
import org.springframework.transaction.annotation.Transactional;


public interface UmsMemberReceiveAddressService {




    /**
     * 修改收货地址
     * @param id 地址表的id
     * @param address 修改的收货地址信息
     */
    @Transactional
    int update(Long id, Address address);

    Address getAddress(Long id);


}
