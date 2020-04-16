package cn.ye.service;


import cn.ye.domain.PerEmail;
import cn.ye.domain.User;

/**
 * 私信服务层规范
 */
public interface PerEmailService {

    /**
     * 查询发送私信的用户
     * @param user
     * @return
     */
    public User findUserByUserFrom(User user);

    /**
     * 查询收到私信的用户
     * @param user
     * @return
     */
    public User findUserByUserTo(User user);

    void sendPerEmail(PerEmail perEmail);
}
