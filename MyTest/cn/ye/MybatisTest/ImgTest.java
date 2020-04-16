package cn.ye.MybatisTest;

import cn.ye.dao.ImgDao;
import cn.ye.domain.Img;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ImgTest {

    private InputStream in;
    private SqlSession sqlSession;
    private ImgDao imgDao;
    private SqlSessionFactory factory;

    /**
     * 初始化资源
     *
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        imgDao = sqlSession.getMapper(ImgDao.class);
    }

    /**
     * 关闭资源
     *
     * @throws Exception
     */
    @After
    public void destroy() throws Exception {
        //关闭资源
        sqlSession.close();
        in.close();
    }

    /**
     * 插入操作
     */
    @Test
    public void insertImg() {
        Img img = new Img();
        img.setImg_name("head_img");
        img.setImg_type("其他");
//        img.setIs_use(true);
        img.setIs_use("true");
        imgDao.insertImg(img);
    }
    /**
     * 删除操作
     */
    @Test
    public void deleteImg() {
        Img img = new Img();
        img.setId(20);
        imgDao.deleteImg(img);
    }
    /**
     * 删除操作
     */
    @Test
    public void updateImg() {
        Img img = new Img();
        img.setId(10);
        img.setIs_use("false");
//        img.setIs_use(false);
        imgDao.updateImg(img);
    }

    /**
     * 查询所有操作
     */
    @Test
    public void findAll() {
        List<Img> imgs = imgDao.findAll();
        for (Img u : imgs) {
            System.out.println(u);
        }
    }    @Test
    public void findAllByTypeAndIsUse() {
        Img img = new Img();
        img.setImg_type("头像");
        img.setIs_use("true");
//        img.setIs_use(true);
        List<Img> imgs = imgDao.findAllByTypeAndIsUse(img);
        for (Img u : imgs) {
            System.out.println(u);
        }
    }

    @Test
    public void findImgByName() {
        Img img = new Img();
        //模糊查询需要传入带通配符的参数，例如下面就是查询姓名中包含"王"的用户
        img.setId(1);
        List<Img> imgs = imgDao.findImgByName(img);
        for (Img u : imgs) {
            System.out.println(u);
        }
    }
}
