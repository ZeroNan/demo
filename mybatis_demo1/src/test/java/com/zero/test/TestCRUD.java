package com.zero.test;

import com.zero.dao.IUserDao;
import com.zero.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: zhangnan
 * @createDate: 2019/4/21
 * @version: 1.0
 */

public class TestCRUD {


    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        //读取配置文件
        in =  Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //使用工厂创建sqlsession对象
        session = factory.openSession();
        //使用sqlsession对象创建Dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
    }

    /**
     * 释放资源
     * @throws IOException
     */
    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }
    @Test
    public void findAll(){

        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println(user);
        }

    }


    @Test
    public void testSave(){
        User u = new User();
        u.setUsername("小明");
        u.setSex("男");
        u.setAddress("黑龙江");
        u.setBirthday(new Date());

        userDao.saveUser(u);
    }




}
