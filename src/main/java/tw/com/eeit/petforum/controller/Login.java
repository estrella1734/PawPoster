package tw.com.eeit.petforum.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.eeit.petforum.model.bean.Member;
import tw.com.eeit.petforum.service.MemberService;

@WebServlet("/Login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		String rememberMe = request.getParameter("rememberMe");

		MemberService memberService = new MemberService();
		Member m = memberService.login(email, password);

		if (m == null) {
			request.setAttribute("message", "登入失敗");
			request.getRequestDispatcher("login").forward(request, response);
		}

		if (m != null) {
			HttpSession session = request.getSession();

			if ("on".equals(rememberMe)) {
				int rememberMeTime=60*60*24*7;
				session.setMaxInactiveInterval(rememberMeTime);
				Cookie c = new Cookie("JSESSIONID", session.getId());
				c.setMaxAge(rememberMeTime);
				response.addCookie(c);
				
			}

				session.setAttribute("loggedInMember", m);
			response.sendRedirect("index");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
