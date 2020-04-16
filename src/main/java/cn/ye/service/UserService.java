package cn.ye.service;


import cn.ye.domain.PageQuery;
import cn.ye.domain.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return 我们注册用户之后判断是否注册成功，然后返回boolean值
     */
    boolean register(User user);

    /**
     * 用户登录
     * @param user
     * @return 由于用户登录成功之后，我们需要获取该用户的数据存入session中，所以设置返回值为User而不是boolean
     */
    User login(User user) throws IOException;


    /**
     * 查询约束条件下的用户数
     * @param user
     * @return
     */
    int findUserTotalCount(User user);

    /**
     * 获取个人的详细数据
     * @param user
     * @return
     */
    User getUserMsg(User user);

    User updateUserMsg(User user);

    PageQuery findAllUserByPageNum(int pageNum);

    User getUserMsgByName(User user);
}
