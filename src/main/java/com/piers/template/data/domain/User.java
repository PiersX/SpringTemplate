package com.piers.template.data.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户表
 *
 * @author piers
 * @version 1.0
 * @since 2018/01/02
 */
@Data
@TableName(value = "awit_management_user")
public class User extends Model<User> {
    /**
     * 主键ID
     */
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**
     * 账号
     */
    @TableField(value="user_name")
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * Email
     */
    private String email;
    /**
     * 备注
     */
    @TableField(value="role_id")
    private Integer roleId;
    /**
     * 状态 1:正常 2:禁用
     */
    @TableField(value="is_lock")
    private Integer isLock;
    /**
     * 创建时间
     */
    @TableField(value="create_time")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
