package cn.ye.ServiceTest;

import cn.ye.domain.PageQuery;
import cn.ye.domain.PerPassage;
import cn.ye.dao.PerPassageDao;
import cn.ye.service.impl.PerPassageServiceImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class PerPassageTest {

    private PerPassageServiceImpl perPassageService=new PerPassageServiceImpl();

    public PerPassageTest() throws IOException {
    }

    /**
     * 分页操作
     */
    @Test
   public void findPerPassageByPerPage(){
        PageQuery allPerMsgByPageNum = perPassageService.findAllPerMsgByPageNum(1);
        System.out.println(allPerMsgByPageNum);
    }
}
