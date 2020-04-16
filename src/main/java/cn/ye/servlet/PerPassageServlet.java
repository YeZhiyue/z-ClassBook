package cn.ye.servlet;

import cn.ye.domain.PerPassage;
import cn.ye.domain.User;
import cn.ye.service.PerPassageService;
import cn.ye.service.impl.ImgServiceImp;
import cn.ye.service.impl.PerPassageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/perPassage/*")
public class PerPassageServlet extends BaseServlet {


    private PerPassageService perPassageService= new PerPassageServiceImpl();;

    public PerPassageServlet() throws IOException {
    }

    public void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取编辑信息
        //封装User对象
        PerPassage perPassage = new PerPassage();
        perPassage.setContent(request.getParameter("content"));
        //日期的自动加入
        perPassage.setDate(new Date());
        perPassage.setEdit_user(((User) request.getSession().getAttribute("user")).getId());
        perPassageService.updatePost(perPassage);
    }

    /**
     *发帖分页查询
     */
    public void findAllPerMsgByPageNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        writeValue(perPassageService.findAllPerMsgByPageNum(Integer.parseInt(request.getParameter("pageNum"))),response);
    }
}
