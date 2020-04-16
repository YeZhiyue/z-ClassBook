package cn.ye.ServiceTest;

import cn.ye.domain.Img;
import cn.ye.dao.ImgDao;
import cn.ye.service.impl.ImgServiceImp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ImgTest {

    private ImgServiceImp imgService=new ImgServiceImp();

    public ImgTest() throws IOException {
    }

    @Test
    public void findAllImgByTypeAndIsUse() {
        for (Img img : imgService.findAllCanUse()) {
            System.out.println(img);
        }
    }
}
