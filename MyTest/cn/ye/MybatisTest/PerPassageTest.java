package cn.ye.MybatisTest;

import cn.ye.dao.PerPassageDao;
import cn.ye.domain.PerPassage;
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

public class PerPassageTest {

    private InputStream in;
    private SqlSession sqlSession;
    private PerPassageDao perPassageDao;
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
        perPassageDao = sqlSession.getMapper(PerPassageDao.class);
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
    public void insertPerPassage() {
        PerPassage perPassage = new PerPassage();
        perPassage.setEdit_user(1);
        perPassage.setDate(new Date());
        perPassage.setContent("用连接池新增了一条数据");
        perPassageDao.insertPerPassage(perPassage);
    }

    /**
     * 删除操作
     */
    @Test
    public void deletePerPassage() {
        PerPassage perPassage = new PerPassage();
        perPassage.setId(4);
        perPassageDao.deletePerPassage(perPassage);
    }

    @Test
    public void updatePerPassage() {
        PerPassage perPassage = new PerPassage();
        perPassage.setId(4);
        perPassage.setContent("数据库更新了发帖！");
        perPassageDao.updatePerPassage(perPassage);
    }

    /**
     * 查询所有操作
     */
    @Test
    public void findAll() {
        List<PerPassage> perPassages = perPassageDao.findAll();
        for (PerPassage u : perPassages) {
            System.out.println(u);
        }
    }

    @Test
    public void findTotalCount() {
        System.out.println(perPassageDao.findTotalCount());
    }

    @Test
    public void findAllOrderByDate() {
        List<PerPassage> perPassages = perPassageDao.findAllOrderByDate();
        for (PerPassage u : perPassages) {
            System.out.println(u);
        }
    }

    @Test
    public void findPerPassageById() {
        PerPassage perPassage = new PerPassage();
        //模糊查询需要传入带通配符的参数，例如下面就是查询姓名中包含"王"的用户
        perPassage.setId(1);
        List<PerPassage> perPassages = perPassageDao.findPerPassageById(perPassage);
        for (PerPassage u : perPassages) {
            System.out.println(u);
        }
    }
    @Test
    public void findPerPassagePerPage() {
        PerPassage perPassage = new PerPassage();
        //模糊查询需要传入带通配符的参数，例如下面就是查询姓名中包含"王"的用户
        perPassage.setId(1);
        List<PerPassage> perPassages = perPassageDao.findPerPassagePerPage(1);
        for (PerPassage u : perPassages) {
            System.out.println(u);
        }
    }
}
