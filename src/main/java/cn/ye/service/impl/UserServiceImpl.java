package cn.ye.service.impl;


import cn.ye.dao.PerEmailDao;
import cn.ye.dao.UserDao;
import cn.ye.domain.PageQuery;
import cn.ye.domain.PerEmail;
import cn.ye.domain.PerPassage;
import cn.ye.domain.User;
import cn.ye.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;
    private SqlSessionFactory factory;
    //    private SqlSession sqlSession = SqlSessionProduce.getSqlSession();
//    private UserDao userDao = sqlSession.getMapper(UserDao.class);
    private User user;
//    private ImgDao imgDao = sqlSession.getMapper(ImgDao.class);
//    private PerEmailDao perEmailDao = sqlSession.getMapper(PerEmailDao.class);
//    private PerPassageDao perPassageDao = sqlSession.getMapper(PerPassageDao.class);

    public UserServiceImpl() throws IOException {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //获取工厂后直接关闭文件IO
        in.close();
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(UserDao.class);
//        sqlSession.close();
    }

    /**
     * 首先判断用户名是否被注册，没有注册那么就进行注册
     *
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {
        sqlSession = factory.openSession(true);
        if (userDao.findUserByName(user) != null) {
            sqlSession.close();
            return false;
        } else {
            userDao.insertUser(user);
            //为了更好的体验，特地添加了新注册用户进行信息发送
            Integer id = (userDao.findUserByName(user)).getId();
            try {
                new PerEmailServiceImpl().sendPerEmail(new PerEmail(1, id, new Date(),"欢迎您的加入，我是通讯录管理员！^_^!"));
                new PerPassageServiceImpl().updatePost(new PerPassage(id,new Date(),"大家好！我是"+user.getName()+"今天我加入了这个大家庭\"^-^\""));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sqlSession.close();
        return true;
    }

    /**
     * 直接根据用户名密码进行登录
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) throws IOException {
        sqlSession = factory.openSession(true);
        User userByNameAndPassword = userDao.findUserByNameAndPassword(user);
        sqlSession.close();
        return userByNameAndPassword;
    }

    /**
     * 可以根据班级分类查询班级的成员数量，或者直接查询整个专业的人数
     *
     * @param user
     * @return
     */
    @Override
    public int findUserTotalCount(User user) {
        sqlSession = factory.openSession(true);
        Integer count = userDao.findTotalCount(user);
        sqlSession.close();
        return count;
    }

    /**
     * 查找用户详细数据
     * @param user
     * @return
     */
    @Override
    public User updateUserMsg(User user) {
        sqlSession=factory.openSession(true);
        userDao.updateUserMsg(user);
        //更新数据完毕之后我们需要再次查询更新后的数据用于判断数据是否更新
        User userById = userDao.findUserById(user);
        sqlSession.close();
        return userById;
    }

    /**
     * 分页查询
     * @param currentPageNum
     * @return
     */
    @Override
    public PageQuery findAllUserByPageNum(int currentPageNum) {
        sqlSession = factory.openSession(true);
        PageQuery pageQuery = new PageQuery();
        Integer count = userDao.findTotalCount(null);
        //1.总人数
        pageQuery.setMsgNum(count);
        //2.每个班的人数
        Map<String, Integer> map = new HashMap<>();
        User user = new User();
        user.setClassName("物联网171");
        map.put(user.getClassName(),userDao.findTotalCount(user));
        user.setClassName("物联网172");
        map.put(user.getClassName(),userDao.findTotalCount(user));
        user.setClassName("物联网173");
        map.put(user.getClassName(),userDao.findTotalCount(user));
        user.setClassName("物联网174");
        map.put(user.getClassName(),userDao.findTotalCount(user));
        pageQuery.setEachClassNum(map);
        //3.总页数
        //根据java的运算机制进行总页数确定
        if (count % 10 != 0) {
            pageQuery.setPageNum(count / 10 + 1);
        } else {
            pageQuery.setPageNum(count / 10);
        }
        //成员数据
        pageQuery.setUsers(userDao.findAllUserByPageNum((currentPageNum-1)*10));
        sqlSession.close();
        return pageQuery;
    }

    /**
     * 用于个人详情页的数据信息
     *
     * @param user
     * @return
     */
    @Override
    public User getUserMsg(User user) {
        sqlSession=factory.openSession(true);
        User userById = userDao.findUserById(user);
        sqlSession.close();
        return userById;
    }

    /**
     * 通过名称查询
     * @param user
     * @return
     */
    @Override
    public User getUserMsgByName(User user) {
        sqlSession = factory.openSession(true);
        User userByName = userDao.findUserByName(user);
        sqlSession.close();
        return userByName;
    }


}
