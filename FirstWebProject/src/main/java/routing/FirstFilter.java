package routing;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FirstFilter
 */
public class FirstFilter extends HttpFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8150861548078986062L;

	//private static int counter = 0;
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public FirstFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		Cookie[] yums = httpRequest.getCookies();
		if (yums != null && httpRequest.getParameter("submit") == null && httpRequest.getSession().getAttribute("username") == null ) {
            for (Cookie yum : yums) {
                if (yum.getName().equals("username")) {
                	yum.setMaxAge(60 * 10);
                    HttpSession session = httpRequest.getSession();
        			session.setAttribute("username", yum.getValue());
        			((HttpServletResponse) response).addCookie(yum);
        			chain.doFilter(request, response);
        			return;
                }
            }
        }
		

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
