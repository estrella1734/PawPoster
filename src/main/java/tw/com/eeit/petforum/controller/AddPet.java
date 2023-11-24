package tw.com.eeit.petforum.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import tw.com.eeit.petforum.model.bean.Member;
import tw.com.eeit.petforum.model.bean.Pet;
import tw.com.eeit.petforum.service.MemberService;

@WebServlet("/AddPet.do")
@MultipartConfig
public class AddPet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	HttpSession session = request.getSession();
	Member loggedInMember =(Member) session.getAttribute("loggedInMember");
	
	String petName = request.getParameter("petName");
	Integer petAge = Integer.valueOf(request.getParameter("petAge"));
	String petType = request.getParameter("petType");
	Part petPhotoPart = request.getPart("petPhoto");
	InputStream in = petPhotoPart.getInputStream();
	byte[] petPhotoContent = in.readAllBytes();
	in.close();
	
	
	Pet p = new Pet();
	p.setpName(petName);
	p.setpAge(petAge);
	p.setType(petType);
	p.setpPhoto(petPhotoContent);
	p.setMember(loggedInMember);
	
	
	MemberService memberService = new MemberService();
	memberService.addPet(p);
	
	response.sendRedirect("profile");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
