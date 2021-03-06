package fr.utbm.store.demo.service;


import fr.utbm.store.demo.config.AdminUserDetails;
import fr.utbm.store.demo.model.UmsAdminParam;
import fr.utbm.store.demo.model.UpdateAdminPasswordParam;
import fr.utbm.store.demo.model.UmsAdmin;
import org.springframework.security.core.userdetails.UserDetails;

public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);
    String adminLogin(String username,String password);
    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */


    /**
     * 根据用户id获取用户
     */



    /**
     * 修改指定用户信息
     */
    int update(Long id, UmsAdmin admin);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    UmsAdmin getCurrentUser();






    /**
     * 修改密码
     */
    int updatePassword(UpdateAdminPasswordParam updatePasswordParam);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    AdminUserDetails loadAdminByUsername(String username);
}
