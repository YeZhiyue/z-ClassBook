package cn.ye.service;


import cn.ye.domain.Img;
import cn.ye.domain.User;

import java.io.IOException;
import java.util.List;

public interface ImgService {
    /**
     * 查询约束条件下的用户数
     * @param user
     * @return
     */
    int findUserTotalCount(User user);

    /**
     * 获取个人的详细数据
     *
     * @return
     */
    List<Img> findAllCanUse();

}
