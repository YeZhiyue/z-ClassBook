package cn.ye.service.impl;


import cn.ye.dao.PerEmailDao;
import cn.ye.domain.PerEmail;
import cn.ye.domain.User;
import cn.ye.service.PerEmailService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class PerEmailServiceImpl implements PerEmailService {
    private InputStream in;
    private SqlSession sqlSession;
    private PerEmailDao perEmailDao;
    private SqlSessionFactory factory;
    //    private SqlSession sqlSession = SqlSessionProduce.getSqlSession();
//    private PerEmailDao perEmailDao = sqlSession.getMapper(PerEmailDao.class);
    private User user;
//    private ImgDao imgDao = sqlSession.getMapper(ImgDao.class);
//    private PerEmailDao perEmailDao = sqlSession.getMapper(PerEmailDao.class);
//    private PerPassageDao perPassageDao = sqlSession.getMapper(PerPassageDao.class);

    public PerEmailServiceImpl() throws IOException {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //获取工厂后直接关闭文件IO
        in.close();
        //4.获取dao的代理对象
        perEmailDao = sqlSession.getMapper(PerEmailDao.class);
    }


    @Override
    public User findUserByUserFrom(User user) {
        return null;
    }

    @Override
    public User findUserByUserTo(User user) {
        return null;
    }

    @Override
    public void sendPerEmail(PerEmail perEmail) {
        sqlSession = factory.openSession(true);
        perEmailDao.insertPerEmail(perEmail);
        sqlSession.close();
    }
}
