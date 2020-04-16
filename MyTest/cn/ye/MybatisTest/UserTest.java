package cn.ye.MybatisTest;

import cn.ye.dao.UserDao;
import cn.ye.domain.User;
import org.apache.ibatis.session.SqlSession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserTest {

    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;
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
        userDao = sqlSession.getMapper(UserDao.class);
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
    public void insertUser() {
        User user = new User(null,"王2五","000000","739253436@qq.com","男","1569201209",
                "head_img0","739153436","15669201209","物联网172",new Date(),new Date(),
                null,null);
        userDao.insertUser(user);
    }

    /**
     * 删除操作
     */
    @Test
    public void deleteUser() {
        User user = new User();
        user.setName("李四");
        userDao.deleteUser(user);
    }

    /**
     * 更新操作
     */
    @Test
    public void updateUser() {
        User user = new User();
        user.setName("张三2");
        userDao.updateUser(user);
    }

    /**
     * 更新用户修改的数据
     */
    @Test
    public void updateUserMsg() {
        User user = new User();
        user.setId(4);
        user.setName("张三3");
        user.setClassName("物联网172");
        userDao.updateUserMsg(user);
    }


    /**
     * 查询所有操作
     */
    @Test
    public void findAll() {
        List<User> users = userDao.findAll();
        for (User u : users) {
            System.out.println(u.getPerEmails());
        }
    }

    @Test
    public void findAllByAlphabetically() {
        List<User> users = userDao.findAllByAlphabetically();
        for (User u : users) {
            System.out.println(u);
        }

    }

    /**
     * 通过姓名查询
     */
    @Test
    public void findUserByName() {
        User user = new User();
        user.setName("叶之越");
        User u = userDao.findUserByName(user);
        System.out.println(u);
    }
    /**
     * 通过ID查询
     */
    @Test
    public void findUserById() {
        User user = new User();
        user.setId(1);
        User u = userDao.findUserById(user);
        System.out.println(u);
    }
    /**
     * 通过姓名和密码查询
     */
    @Test
    public void findUserByNameAndPassword() {
        User user = new User();
        user.setName("叶之越");
        user.setPassword("000000");
        User u = userDao.findUserByName(user);
            System.out.println(u);
    }
    /**
     * 关键字模糊查询
     */
    @Test
    public void findByKeyWord() {
        User user = new User();
        //模糊查询需要传入带通配符的参数，例如下面就是查询姓名中包含"王"的用户
        user.setName("%叶%");
        List<User> users = userDao.findByKeyWord(user);
        for (User u : users) {
            System.out.println(u);
        }
    }

    /**
     * 查询总数
     */
    @Test
    public void findTotalCount() {
        User user = new User();
        user.setClassName("物联网172");
        Integer count = userDao.findTotalCount(user);
        System.out.println("查询到的数量：" + count);
    }
}
