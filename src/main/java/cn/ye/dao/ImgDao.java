package cn.ye.dao;

import cn.ye.domain.Img;

import java.util.List;

/**
 * CRUD 这个是一个数据库最基础的操作
 *  增删改
 *  查询
 *  模糊查询
 *  聚合函数查询
 * @author Ye
 */
public interface ImgDao {
    /**
     * 为什么我下面的参数选择img，因为这样就方便选择img
     * 属性中的任意属性进行数据库表的操作
     * @param img
     */
    void updateImg(Img img);

    /**
     * 插入操作
     * @param img
     */
    void insertImg(Img img);

    /**
     * 删除操作
     * @param img
     */
    void deleteImg(Img img);

    /**
     * 查询所有
     * @return
     */
    List<Img> findAll();

    /**
     * 通过名称查找
     * @param img
     * @return
     */
    List<Img> findImgByName(Img img);

    /**
     * 通过关键字查找
     * @param img
     * @return
     */
    List<Img> findByKeyWord(Img img);

    /**
     * 查询总数
     * @param img
     * @return
     */
    Integer findTotalCount(Img img);

    List<Img> findAllByType(Img img);

    List<Img> findAllByTypeAndIsUse(Img img);
}
