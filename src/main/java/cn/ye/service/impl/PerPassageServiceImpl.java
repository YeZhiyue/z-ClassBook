package cn.ye.service.impl;


import cn.ye.dao.PerPassageDao;
import cn.ye.domain.PageQuery;
import cn.ye.domain.PerPassage;
import cn.ye.domain.User;
import cn.ye.service.PerPassageService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PerPassageServiceImpl implements PerPassageService {
    private InputStream in;
    private SqlSession sqlSession;
    private PerPassageDao perPassageDao;
    private SqlSessionFactory factory;


    //    private SqlSession sqlSession = SqlSessionProduce.getSqlSession();
//    private PerEmailDao perEmailDao = sqlSession.getMapper(PerEmailDao.class);
    private User user;
//    private ImgDao imgDao = sqlSession.getMapper(ImgDao.class);
//    private PerEmailDao perEmailDao = sqlSession.getMapper(PerEmailDao.class);
//    private PerPassageDao perPassageDao = sqlSession.getMapper(PerPassageDao.class);

    public PerPassageServiceImpl() throws IOException {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //获取工厂后直接关闭文件IO
        in.close();
        //4.获取dao的代理对象
        perPassageDao = sqlSession.getMapper(PerPassageDao.class);
    }

    @Override
    public void updatePost(PerPassage perPassage) {
        sqlSession = factory.openSession(true);
        perPassageDao.insertPerPassage(perPassage);
        sqlSession.close();
    }

    @Override
    public PageQuery findAllPerMsgByPageNum(int pageNum) {
        sqlSession = factory.openSession(true);

        PageQuery pageQuery = new PageQuery();
        //总信息条数 查询
        Integer count = perPassageDao.findTotalCount();
        pageQuery.setMsgNum(count);
        //根据java的运算机制进行总页数确定
        if (count % 12 != 0) {
            pageQuery.setPageNum(count / 12 + 1);
        } else {
            pageQuery.setPageNum(count / 12);
        }
        //设置文章内容信息
        List<PerPassage> passageList = perPassageDao.findPerPassagePerPage((pageNum - 1) * 12);
        pageQuery.setPerPassages(passageList);
        sqlSession.close();
        return pageQuery;
    }


}
