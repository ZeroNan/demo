package com.zero.dao;

import com.zero.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * @since v1.0.0
 * <PRE>
 * author zhangnan
 * date 2019/4/20
 * </PRE>
 */
public interface IUserDao {
    /**
     * 查询所有
     *
     */
    List<User> findAll();

    void saveUser(User user);
}
