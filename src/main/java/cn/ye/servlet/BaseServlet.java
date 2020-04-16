package cn.ye.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 注意：这个是当前项目所有Servlet资源的父类，这个项目下面的所有Servlet资源都需要继承这个类
 *  1.意味着这个类可以写一些共性的方法让其每一个子类所共享
 *  2.如果不继承这个类，那么就意味着浏览器访问不到没有继承这个类的Servlet资源
 * 方法分发Servlet
 *  1.获取浏览器地址栏的URI字符串
 *  2.提取其中获取Servlet资源中的方法
 *  3.通过反射获取Servlet资源对象，接着进一步获取其中方法名称对应的方法（因为Servlet中的方法参数的特殊性，所以这一步实现起来比较简单）
 *  4.执行通过反射获取的方法
 */
public class BaseServlet extends HttpServlet {
    //用于所有用户的总计数，控制台打印
    private static int count=1;

    /**
     * 实现了Servlet资源的进一步模块化，方便以后的维护和管理
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求路径
        String uri = req.getRequestURI(); //   /user（路径名）/add(方法名)
        //2.获取方法名称
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        //控制台打印，方便后期的调试
        System.out.println("-------------------打印第"+count+++"个请求-------------------------"+
                "\n"+"  1.请求URI链接详情:  "+uri+//  uri链接/travel/user
                "\n"+"  2.URI中方法名称： "+methodName+//方法名称 /add
                "\n  3."+this);//UserServlet的对象cn.itcast.travel.web.servlet.UserServlet@4903d97e//谁调用我？我代表谁
        //3.获取方法对象Method
        try {
            //获取方法
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //4.执行方法
            //暴力反射，不推荐这种方式
            //method.setAccessible(true);
            //最后我们选择了修改Servlet中方法的访问权限为public，这样就可以访问到Servlet资源中的每一个方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    /**
     * 直接将传入的对象序列化为json，并且写回客户端
     * @param obj
     */
    public void writeValue(Object obj,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();//不要写到类变量当中去，容易发生安全问题
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),obj);
    }

    /**
     * 将传入的对象序列化为json，返回
     * @param obj
     * @return
     */
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
