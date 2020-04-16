package cn.ye.dao;

import cn.ye.domain.PerEmail;

import java.util.List;

/**
 * CRUD 这个是一个数据库最基础的操作
 *  增删改
 *  查询
 *  模糊查询
 *  聚合函数查询
 * @author Ye
 */
public interface PerEmailDao {
    /**
     * 为什么我下面的参数选择perEmail，因为这样就方便选择perEmail
     * 属性中的任意属性进行数据库表的操作
     * @param perEmail
     */
    void updatePerEmail(PerEmail perEmail);

    /**
     * 插入操作
     * @param perEmail
     */
    void insertPerEmail(PerEmail perEmail);

    /**
     * 删除操作
     * @param perEmail
     */
    void deletePerEmail(PerEmail perEmail);

    /**
     * 查询所有
     * @return
     */
    List<PerEmail> findAll();

    /**
     * 通过名称查找
     * @param perEmail
     * @return
     */
    List<PerEmail> findUserByUserFrom(PerEmail perEmail);


    /**
     * 通过关键字查找
     * @return
     */
    List<PerEmail> findUserByUserTo(PerEmail perEmail);
}
