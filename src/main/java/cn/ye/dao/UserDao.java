package cn.ye.dao;

import cn.ye.domain.User;

import java.util.List;

/**
 * CRUD 这个是一个数据库最基础的操作
 *  增删改
 *  查询
 *  模糊查询
 *  聚合函数查询
 * @author Ye
 */
public interface UserDao {
    /**
     * 为什么我下面的参数选择user，因为这样就方便选择user
     * 属性中的任意属性进行数据库表的操作
     * @param user
     */
    void updateUser(User user);

    /**
     * 插入操作
     * @param user
     */
    void insertUser(User user);

    /**
     * 删除操作
     * @param user
     */
    void deleteUser(User user);

    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 通过名称查找
     * @param user
     * @return
     */
    User findUserByName(User user);

    User findUserByNameAndPassword(User user);

    /**
     * 通过关键字查找
     * @param user
     * @return
     */
    List<User> findByKeyWord(User user);

    /**
     * 查询总数
     * @param user
     * @return
     */
    Integer findTotalCount(User user);

    List<User> findAllByAlphabetically();

    User findUserById(User user);

    void updateUserMsg(User user);

    List<User> findAllUserByPageNum(int currentPageNum);
}
