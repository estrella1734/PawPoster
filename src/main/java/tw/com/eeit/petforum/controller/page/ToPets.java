package tw.com.eeit.petforum.controller.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.eeit.petforum.model.bean.Pet;
import tw.com.eeit.petforum.service.MemberService;
import tw.com.eeit.petforum.util.PathConverter;

@WebServlet("/pets")
public class ToPets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MemberService memberService = new MemberService();
		List<Pet> pets = memberService.getAllPets();
		
		
		request.setAttribute("pets", pets);
		
		request.getRequestDispatcher(PathConverter.convertToWebInfPath(request.getServletPath()))
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
