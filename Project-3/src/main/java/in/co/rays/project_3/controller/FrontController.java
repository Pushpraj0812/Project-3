package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.rays.project_3.util.ServletUtility;

/**
 * Front Functionality ctl. to perform session checking and logging operation
 * @author Pushpraj Singh Kachhaway
 *
 */
@WebFilter(urlPatterns={"/ctl/*"})
public class FrontController implements Filter {
	
	private static Logger log = Logger.getLogger(FrontController.class);
	
	public void init(FilterConfig conf) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		log.debug("FrontController dofilter start");
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		HttpSession session = request.getSession();
		
		String uri=request.getRequestURI();
		request.setAttribute("uri", uri);
		
		if (session.getAttribute("user") == null) {
			request.setAttribute("error", "Your session has been expired please Login again!");
			ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
		} else {
			chain.doFilter(req, resp);
		}
		log.debug("FrontController dofilter end");
	}

	public void destroy() {
	}
}