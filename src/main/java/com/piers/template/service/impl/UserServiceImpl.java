package com.piers.template.service.impl;

import com.piers.template.data.domain.User;
import com.piers.template.data.vo.UserVO;
import com.piers.template.repository.UserRepository;
import com.piers.template.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * User Service Implement
 *
 * @Author: piers
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserRepository userRepository;

    @Resource
    private RedisTemplate<String, User> redisTemplate;


    /**
     * 创建用户
     * 不会对缓存做任何操作
     */
    @Override
    public int createUser(User user) {
        logger.info("创建用户start...");
        return userRepository.insert(user);
    }

    /**
     * 获取用户信息
     * 如果缓存存在，从缓存中获取城市信息
     * 如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
     *
     * @param id 用户ID
     * @return 用户
     */
    @Override
    public UserVO getById(int id) {
        logger.info("获取用户start...");
        // 从缓存中获取用户信息
        String key = "user_" + id;
        ValueOperations<String, User> operations = redisTemplate.opsForValue();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            User user = operations.get(key);
            logger.info("从缓存中获取了用户 id = " + id);
            return parseToUserVO(user);
        }

        // 缓存不存在，从 DB 中获取
        User user = userRepository.selectById(id);
        logger.info("selectById user = " + user);
        if(user != null) {
            // 插入缓存
            operations.set(key, user, 10, TimeUnit.SECONDS);
            logger.info("operations set key = " + key);
        }

        return parseToUserVO(user);
    }

    /**
     * 更新用户
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     *
     * @param user 用户
     */
    @Override
    public int updateUser(User user) {
        logger.info("更新用户start...");
        int ret = userRepository.updateById(user);

        // 缓存存在，删除缓存
        deleteRedisKey(user.getId());
        return ret;
    }

    /**
     * 删除用户
     * 如果缓存中存在，删除
     */
    @Override
    public int deleteById(int id) {
        logger.info("删除用户start...");
        int ret = userRepository.deleteById(id);

        // 缓存存在，删除缓存
        deleteRedisKey(id);
        return ret;
    }

    private void deleteRedisKey(int id) {
        String key = "user_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            logger.info("更新用户时候，从缓存中删除用户 >> " + id);
        }
    }


    private UserVO parseToUserVO(User user) {
        UserVO userVO = new UserVO();
        if(user != null) {
            userVO.setUserName(user.getUserName());
            userVO.setMobile(user.getMobile());
            userVO.setEmail(user.getEmail());
            userVO.setRoleId(user.getRoleId());
        }
        return userVO;
    }




}