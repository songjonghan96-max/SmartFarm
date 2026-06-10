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
<<<<<<< HEAD
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 초기화 시 필요한 로직 (비워두셔도 됩니다)
    }
=======
    public void init(FilterConfig filterConfig) throws ServletException {}
>>>>>>> b3a49bc8b1a45ff682ec870fcf26673b263eb686

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

<<<<<<< HEAD
        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        
        // ContextPath를 제외한 순수 매핑 주소 추출
        String path = requestURI.substring(contextPath.length());

        // 💡 1. 필터를 거치지 않고 통과시킬 제외 주소 설정
        boolean isExcludedPath = path.equals("/login") 
                              || path.equals("/searchpw") 
                              || path.equals("/changepw")
                              || path.startsWith("/resources/")  // CSS, JS, 이미지 폴더 제외
                              || path.startsWith("/static/");

        // 💡 2. 로그인 여부 확인 (우리가 앞서 세션에 담았던 "loginUser" 키값 기준)
        boolean isLoggedIn = (session != null && session.getAttribute("loginUser") != null);

        if (isExcludedPath || isLoggedIn) {
            // 제외 주소이거나 로그인이 되어있다면 정상 진행
            chain.doFilter(request, response);
        } else {
            // 로그인도 안 됐고 제외 주소도 아니라면 로그인 페이지로 회귀(리다이렉트)
=======
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
>>>>>>> b3a49bc8b1a45ff682ec870fcf26673b263eb686
            httpResponse.sendRedirect(contextPath + "/login");
        }
    }

    @Override
<<<<<<< HEAD
    public void destroy() {
        // 필터 종료 시 로직
    }
=======
    public void destroy() {}
>>>>>>> b3a49bc8b1a45ff682ec870fcf26673b263eb686
}
