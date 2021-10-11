package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class EncodingFilter
 */
//1. Annotation을 통한 매핑
// "/*"로 지정하게 되면 모든 요청을 의미함
@WebFilter("/*")

//2. web.xml 파일에 필터를 등록하는 매핑(다양한 필터를 사용할것이면 순서를설정할수잇도록)
//어노테이션 방식의 경우는 지정불가. 

public class EncodingFilter implements Filter {
	//서블릿 필터는 request, response가 서블릿에 도달하기 전 필요한 전/후 처리작업을 맡는다. 
	//필터는 FilterChain을 통해 여러개 혹은 연쇄적으로 사용 가능하다.
    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		//컨테이너가 필터를 제거할 때 호출 서버 종료
		//System.out.println("필터 인스턴스가 소멸됩니다. ");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		// 서블릿 수행 전 필터 동작
		//System.out.println("doFilter 동작합니다.");
		
		//전송방식이 post일때 반드시 request에 대해서 utf-8로 인코딩하는 처리
		HttpServletRequest hreq = (HttpServletRequest)request;
		if(hreq.getMethod().equalsIgnoreCase("post")) {//대소문자상관없이 post라면
			request.setCharacterEncoding("utf-8");
		}
		//->적용이 잘 되었는지 확인하기 위해 memberJoin, memberModify 요청 처리 서블릿에서
		// 인코딩 구문 주석 후 기능 수행해보기
		
		
		
		//FilterChain의 doFilter는 다음 필터를 호출하거나, 마지막 필터라면 서블릿으로 넘어감.
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		
		//서블릿 수행 후 필터동작
		//System.out.println("doFilter 이후 처리되는 동작입니다.");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	//	컨테이너가 필터를 인스턴스화할때 호출 -> 필터 설정관련 코드작성 가능
		//System.out.println("EncodingFilter 초기화 되었습니다.");
	}

}
