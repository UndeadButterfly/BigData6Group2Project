package com.twy.tripwithyou_spring.inerceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    Logger log= LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        Object loginUser_obj=session.getAttribute("loginInfo");
        String url = request.getRequestURI();
        String queryString = request.getQueryString();
        url+=(queryString!=null)?"?"+queryString:"";
        System.out.println(url);
        if(loginUser_obj==null){
            session.setAttribute("msg","로그인 후 이용가능 한 서비스 입니다.");
            session.setAttribute("redirectUri",url);
            response.sendRedirect("/user/login.do");
            return false;
        }
        return true;
    }
}
