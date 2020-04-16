package cn.ye.dao;

import cn.ye.domain.PerPassage;

import java.util.List;

/**
 * CRUD 这个是一个数据库最基础的操作
 *  增删改
 *  查询
 *  模糊查询
 *  聚合函数查询
 * @author Ye
 */
public interface PerPassageDao {
    /**
     * 为什么我下面的参数选择perPassage，因为这样就方便选择perPassage
     * 属性中的任意属性进行数据库表的操作
     * @param perPassage
     */
    void updatePerPassage(PerPassage perPassage);

    /**
     * 插入操作
     * @param perPassage
     */
    void insertPerPassage(PerPassage perPassage);

    /**
     * 删除操作
     * @param perPassage
     */
    void deletePerPassage(PerPassage perPassage);

    /**
     * 查询所有
     * @return
     */
    List<PerPassage> findAll();

    /**
     * 通过名称查找
     * @param perPassage
     * @return
     */
    List<PerPassage> findPerPassageByName(PerPassage perPassage);

    /**
     * 通过关键字查找
     * @param perPassage
     * @return
     */
    List<PerPassage> findByKeyWord(PerPassage perPassage);

    List<PerPassage> findPerPassageById(PerPassage perPassage);

    /**
     * 查询总数
     * @return
     */
    Integer findTotalCount();

    List<PerPassage> findAllOrderByDate();

    List<PerPassage> findPerPassagePerPage(int currentPage);
}
