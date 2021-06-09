package fr.utbm.store.demo.controller;

import fr.utbm.store.demo.api.CommonResult;
import fr.utbm.store.demo.bo.AdminUserDetails;
import fr.utbm.store.demo.model.Address;
import fr.utbm.store.demo.model.UmsAdmin;
import fr.utbm.store.demo.service.UmsAdminService;
import fr.utbm.store.demo.service.UmsMemberReceiveAddressService;
import fr.utbm.store.demo.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@Api(tags = "UmsMemberReceiveAddressController", description = "会员收货地址管理")
@RequestMapping("/user/address")
public class UmsMemberReceiveAddressController {
    @Autowired
    private UmsMemberReceiveAddressService memberReceiveAddressService;
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @ApiOperation("添加收货地址")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestBody Address address) {
       String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AdminUserDetails umsAdmin=umsAdminService.loadAdminByUsername(username);
        int count = memberReceiveAddressService.update(umsAdmin.getUmsAdmin().getId(),address);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
        @ApiOperation("获取收货地址")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAddress() {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            AdminUserDetails umsAdmin=umsAdminService.loadAdminByUsername(username);
            Address address = memberReceiveAddressService.getAddress(umsAdmin.getUmsAdmin().getId());
            if (address!=null) {
                return CommonResult.success(address);
            }
            return CommonResult.failed();
    }

}
