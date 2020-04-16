package cn.ye.service.impl;

import cn.ye.dao.ImgDao;
import cn.ye.domain.Img;
import cn.ye.domain.User;
import cn.ye.service.ImgService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ImgServiceImp implements ImgService {

    private InputStream in;
    private SqlSession sqlSession;
    private ImgDao imgDao;
    private SqlSessionFactory factory;
    //    private SqlSession sqlSession = SqlSessionProduce.getSqlSession();
//    private UserDao userDao = sqlSession.getMapper(UserDao.class);
    private User user;
//    private ImgDao imgDao = sqlSession.getMapper(ImgDao.class);
//    private PerEmailDao perEmailDao = sqlSession.getMapper(PerEmailDao.class);
//    private PerPassageDao perPassageDao = sqlSession.getMapper(PerPassageDao.class);

    public ImgServiceImp() throws IOException {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //获取工厂后直接关闭文件IO
        in.close();
        //4.获取dao的代理对象
        imgDao = sqlSession.getMapper(ImgDao.class);
    }

    @Override
    public int findUserTotalCount(User user) {
        return 0;
    }

    @Override
    public List<Img> findAllCanUse() {
        sqlSession = factory.openSession(true);
        Img img = new Img();
        img.setImg_type("头像");
        img.setIs_use("true");
        List<Img> allByTypeAndIsUse = imgDao.findAllByTypeAndIsUse(img);
        sqlSession.close();
        return allByTypeAndIsUse;
    }

    /**
     * 关闭与数据库之间连接
     */
    public void closeSqlSession() {
        sqlSession.close();
    }

}
