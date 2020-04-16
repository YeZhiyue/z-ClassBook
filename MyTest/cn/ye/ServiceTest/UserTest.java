package cn.ye.ServiceTest;

import cn.ye.domain.User;
import cn.ye.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

public class UserTest {

    private UserServiceImpl userService;

    @Before
    public void init() throws IOException {
        userService = new UserServiceImpl();
    }

    @Test
    public void login() throws IOException {
        User user = new User();
        user.setName("叶之越");
        user.setPassword("000000");
        System.out.println(userService.login(user));
    }

    @Test
    public void register() throws IOException {
        User user = new User();
        user.setName("叶之越");
//        user.setName("六六");
        user.setPassword("000000");
        user.setEmail("647832");
        user.setSex("男");
        user.setPhone("143414");
        user.setImg("123");
        user.setClassName("物联网172");
        user.setBirthday(new Date());
        user.setLogin_data(new Date());
        userService.register(user);
    }

    @Test
    public void findTotalCount() throws IOException {
        User user = new User();
//        user.setClassName("通信");
        System.out.println(userService.findUserTotalCount(user));
    }

    @Test
    public void getUserMsg() throws IOException {
        User user = new User();
        user.setId(1);
//        System.out.println(userService.getUserMsg(user));
        System.out.println(userService.getUserMsg(user).getSendEmails());
//        System.out.println(userService.getUserMsg(user).getPerPassages());
    }
    @Test
    public void getUserMsg2() throws IOException {
        User user = new User();
        user.setId(1);
//        System.out.println(userService.getUserMsg(user));
        System.out.println(userService.getUserMsg(user).getSendEmails());
//        System.out.println(userService.getUserMsg(user).getPerPassages());
    }

    /**
     * 分页查询
     *
     * @throws IOException
     */
    @Test
    public void findAllUserByPageNum() throws IOException {
        User user = new User();
        user.setName("叶之越");
        System.out.println(userService.findAllUserByPageNum(1));
//        System.out.println(userService.getUserMsg(user).getPerPassages());
    }

}
