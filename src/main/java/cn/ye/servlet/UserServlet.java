package cn.ye.servlet;

import cn.ye.domain.PerPassage;
import cn.ye.domain.ResultInfo;
import cn.ye.domain.User;
import cn.ye.service.UserService;
import cn.ye.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static java.awt.SystemColor.info;

@WebServlet("/user/*") // /user/add /user/find
public class UserServlet extends BaseServlet {

    //声明UserService业务对象
    private UserService userService = new UserServiceImpl();

    public UserServlet() throws IOException, InvocationTargetException, IllegalAccessException {
    }

    /**
     * 注册
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取表单信息
        Map<String, String[]> map = request.getParameterMap();
        //2.封装User对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //在这里添加用户的注册时间
        user.setLogin_data(new Date());
        //3.如果注册成功
        ResultInfo info = new ResultInfo();
        if (userService.register(user)) {
            info.setFlag(true);
            info.setErrorMsg("恭喜您称为了物联网家庭的一员！！！");
        } else {
            //4.如果注册失败
            info.setFlag(false);
            info.setErrorMsg("用户名已经被注册，换个用户名吧！！！");
        }
        //5.将info对象序列化为json
        writeValue(info, response);
    }

    /**
     * 用户登录
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名和密码数据
        System.out.println("helo");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2.封装User对象
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        //3.如果登录成功,将用户存入session中
        ResultInfo info = new ResultInfo();
        if (userService.login(user) != null) {
            //用session保存用户数据，因为用户信息比较常用并且私密
            //在此处发现一个bug，本来是直接存入登录账户的信息，但是发现在之后的编码中出现信息不足，在此将完整用户数据存入session中
            request.getSession().setAttribute("user", userService.getUserMsgByName(user));
            info.setFlag(true);
            info.setErrorMsg("登录成功！");
        } else {
            //4.如果登录失败
            info.setFlag(false);
            info.setErrorMsg("用户名或者密码错误！");
        }
        //5.响应数据
        writeValue(info, response);
    }

    /**
     * 查找用户详细数据
     */

    public void userMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //用于判断用户是否是本用户
        String mateId = request.getParameter("mateId");
        //获取session中用户登录信息
        User user = (User) request.getSession().getAttribute("user");
        //如果用户没有登录，那么就不能进行个人信息的访问
        if (user != null) {
            User userMsg;
            //如果id和登录用户id不同(并且获取不为空)，说明这个用户并非当前用户，我们就不要将该用户的私密信息透露出去
            if (mateId == "" || mateId == null) {
                userMsg = userService.getUserMsg(user);
                //设置是登陆者本人
                userMsg.setIsHost(true);
            } else if (Integer.parseInt(mateId) != user.getId()) {
                //说明现在需要查询其他用户的信息
                userMsg = userService.getUserMsg(new User(Integer.parseInt(mateId)));
                //把私密信息清空
                userMsg.setPerEmails(null);
                userMsg.setSendEmails(null);
                //设置不是登陆者本人
                userMsg.setIsHost(false);
            } else {
                userMsg = userService.getUserMsg(user);
                //设置是登陆者本人
                userMsg.setIsHost(true);
            }
            writeValue(userMsg, response);
        }
    }

    /**
     * 分页查询
     */
    public void findAllUserByPageNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        writeValue(userService.findAllUserByPageNum(Integer.parseInt(request.getParameter("pageNum"))), response);
    }

    /**
     * 用于判断用户是否已经登录
     */
    public void isLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        writeValue(user, response);
    }

    public void updateUserMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取编辑信息
        //封装User对象
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setClassName(request.getParameter("className"));
        user.setHobby(request.getParameter("hobby"));
//        user.setBirthday(request.getParameter("birthday"));
        Date birthday = null;
        try {
            birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirthday(birthday);
        user.setIntroduce(request.getParameter("introduce"));
        //3.如果注册成功
        //这里需要通过session中的数据进行用户的更新
        user.setId(((User) request.getSession().getAttribute("user")).getId());
        //如果更新前后的信息一致，那么表示数据没有更新，我们就不必返回数据
        User newUser = userService.updateUserMsg(user);
        if (!newUser.equals(user)) {
            request.getSession().setAttribute("user", newUser);
            writeValue(newUser, response);
        }
    }

    /**
     * 用户退出
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁session
        request.getSession().invalidate();
        //2.跳转登录页面
        response.sendRedirect(request.getContextPath() + "/login.html");
    }
}
