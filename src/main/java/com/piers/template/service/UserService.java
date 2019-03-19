package com.piers.template.service;


import com.piers.template.data.domain.User;
import com.piers.template.data.vo.UserVO;

/**
 * User Service
 *
 * @Author: piers
 */
public interface UserService {

    /**
     * 创建用户
     * @param user
     * @return
     */
    int createUser(User user);

    /**
     * 获取用户信息
     * 如果缓存存在，从缓存中获取城市信息
     * 如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
     *
     * @param id 用户ID
     * @return 用户
     */
    UserVO getById(int id);

    /**
     * 更新用户
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     *
     * @param user 用户
     * @return
     */
    int updateUser(User user);


    /**
     * 删除用户
     * 如果缓存中存在，删除
     * @param id
     * @return
     */
    int deleteById(int id);

}