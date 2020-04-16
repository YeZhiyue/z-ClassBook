package cn.ye.MybatisTest;

import cn.ye.dao.PerEmailDao;
import cn.ye.domain.PerEmail;
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

public class PerEmailTest {

    private InputStream in;
    private SqlSession sqlSession;
    private PerEmailDao perEmailDao;
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
        perEmailDao = sqlSession.getMapper(PerEmailDao.class);
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
    public void insertPerEmail() {
        PerEmail perEmail = new PerEmail();
        perEmail.setContent("新增加了一条私信数据");
        perEmail.setDate(new Date());
        perEmail.setUser_from(1);
        perEmail.setUser_to(1);
//        perEmail.getDate(new SimpleDateFormat("yyyy-MM-rr HH-mm-ss").format(new Date()));
        perEmailDao.insertPerEmail(perEmail);
    }


    /**
     * 删除操作
     */
    @Test
    public void deletePerEmail() {
        PerEmail perEmail = new PerEmail();
        perEmail.setId(3);
        perEmailDao.deletePerEmail(perEmail);
    }
    @Test
    public void updatePerEmail() {
        PerEmail perEmail = new PerEmail();
        //1.找到需要更新的私信号码
        perEmail.setId(1);
        //2.设置更新内容
        perEmail.setContent("数据库更新了一条信息");
        perEmailDao.updatePerEmail(perEmail);
    }

    /**
     * 查询所有操作
     */
    @Test
    public void findAll() {
        List<PerEmail> perEmails = perEmailDao.findAll();
        for (PerEmail u : perEmails) {
            System.out.println(u);
        }
    }



    @Test
    public void findPerEmailByPerEmailTo() {
        PerEmail perEmail = new PerEmail();
        perEmail.setUser_to(1);
        List<PerEmail> list = perEmailDao.findUserByUserTo(perEmail);
        for (PerEmail email : list) {
            System.out.println(email);
        }
    }

    @Test
    public void findPerEmailByPerEmailFrom() {
        PerEmail perEmail = new PerEmail();
        perEmail.setUser_from(1);
        List<PerEmail> list = perEmailDao.findUserByUserFrom(perEmail);
        for (PerEmail email : list) {
            System.out.println(email);
        }
    }
}
