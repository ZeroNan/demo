package com.zero.test;


import com.zero.dao.IUserDao;
import com.zero.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @description:
 * @author: zhangnan
 * @createDate: 2019/4/20
 * @version: 1.0
 */

public class MybatisTest {
    /**
     * 入门测试
     */
    public static void main(String[] args) throws IOException {
        findAllDemo();

    }

    private static void findAllDemo() throws IOException {
        //读取配置文件
        InputStream in =  Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //使用工厂创建sqlsession对象
        SqlSession session = factory.openSession();
        //使用sqlsession对象创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        //使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
        //释放资源
        session.clearCache();
        in.close();
    }


}
