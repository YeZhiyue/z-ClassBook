package cn.ye.servlet;

import cn.ye.domain.PerEmail;
import cn.ye.domain.User;
import cn.ye.service.impl.PerEmailServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/perEmail/*")
public class PerEmailServlet extends BaseServlet {


    private PerEmailServiceImpl perEmailService = new PerEmailServiceImpl();;

    public PerEmailServlet() throws IOException {
    }

    /**
     * 用于返回所有头像路径
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void sendPerEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer user_from = ((User) request.getSession().getAttribute("user")).getId();
        Integer user_to=Integer.parseInt(request.getParameter("user_to"));
        String content=(String)request.getParameter("content");
        Date date = new Date();
        PerEmail perEmail = new PerEmail();
        perEmail.setUser_from(user_from);
        perEmail.setUser_to(user_to);
        perEmail.setDate(date);
        perEmail.setContent(content);
        perEmailService.sendPerEmail(perEmail);
    }
}
