package cn.ye.service;

import cn.ye.domain.PageQuery;
import cn.ye.domain.PerPassage;

public interface PerPassageService {

    void updatePost(PerPassage perPassage);

    PageQuery findAllPerMsgByPageNum(int pageNum);
}
