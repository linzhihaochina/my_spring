package com.youngforcoding.servlet;

import com.youngforcoding.factory.ProxyFactory;
import com.youngforcoding.pojo.Result;
import com.youngforcoding.service.TransferService;
import com.youngforcoding.util.JsonUtils;
import com.youngforcoding.util.SpringContextUtil;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-18 15:45   
 *  *    
 *  
 */
@WebServlet(name = "transferServlet", urlPatterns = "/transferServlet")
public class TransferServlet extends HttpServlet {

    /**
     * 不通过web容器启动Spring容器，自己启动Spring容器
     */
//    private ProxyFactory proxyFactory = (ProxyFactory) SpringContextUtil.getBean("proxyFactory");
//    private TransferService transferService = (TransferService) proxyFactory.getJdkProxy(SpringContextUtil.getBean("transferService"));

    private TransferService transferService;


    @Override
    public void init() throws ServletException {
        //  通过ServletContext获取Spring容器(Spring容器将WebApplicationContext注入到了ServletContext中)
        //  WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());

        WebApplicationContext webApplicationContext = (WebApplicationContext) this.getServletContext()
                .getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
        ProxyFactory proxyFactory = (ProxyFactory) webApplicationContext.getBean("proxyFactory");
        transferService = (TransferService) proxyFactory.getJdkProxy(webApplicationContext.getBean("transferService"));
    }

    public TransferServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // 设置请求体的字符编码
        req.setCharacterEncoding("UTF-8");

        String fromCardNo = req.getParameter("fromCardNo");
        String toCardNo = req.getParameter("toCardNo");
        String moneyStr = req.getParameter("money");
        int money = Integer.parseInt(moneyStr);

        Result result = new Result();

        try {

            // 2. 调用service层方法
            transferService.transfer(fromCardNo, toCardNo, money);
            result.setStatus("200");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("201");
            result.setMessage(e.toString());
        }

        // 响应
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(JsonUtils.object2Json(result));
    }
}
