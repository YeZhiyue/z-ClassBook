package cn.ye.servlet;

import cn.ye.dao.ImgDao;
import cn.ye.dao.UserDao;
import cn.ye.domain.User;
import cn.ye.service.impl.ImgServiceImp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/img/*")
public class ImgServlet extends BaseServlet {


    private ImgServiceImp imgService= new ImgServiceImp();;

    public ImgServlet() throws IOException {
    }

    /**
     * 用于返回所有头像路径
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAllImgCanUse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            writeValue(imgService.findAllCanUse(),response);
    }
}
