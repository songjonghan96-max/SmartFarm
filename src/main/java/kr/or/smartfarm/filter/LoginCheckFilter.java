package kr.or.smartfarm.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        // 💡 [추가] 필터에서 나가는(Response) 데이터도 한글 깨짐 방지 강제 처리
        httpRequest.setCharacterEncoding("UTF-8");
        httpResponse.setCharacterEncoding("UTF-8");

        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String path = requestURI.substring(contextPath.length());

        // 필터를 거치지 않고 통과시킬 제외 주소 (엑셀 다운로드 URL이 있다면 여기에 추가 가능)
        boolean isExcludedPath = path.equals("/login") 
                              || path.equals("/searchpw") 
                              || path.equals("/changepw")
                              || path.startsWith("/resources/")  
                              || path.startsWith("/static/");

        boolean isLoggedIn = (session != null && session.getAttribute("loginUser") != null);

        if (isExcludedPath || isLoggedIn) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(contextPath + "/login");
        }
    }

    @Override
    public void destroy() {}
}
