package com.awd.bp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.awd.bp.beans.User;
import com.awd.bp.data.UserDAO;

/**
 * Servlet implementation class LoginControllerServlet
 */
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControllerServlet() {
        super();
        userDAO = new UserDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "/login.jsp";
        String message = null;
        HttpSession httpSession = request.getSession();

        String email =request.getParameter("email");
        String pswd=request.getParameter("password");

        if(email.isEmpty() || pswd.isEmpty() ) {
            message = "Please fill out all the boxes";
            url = "/login.jsp";
        }else if(userDAO.validate(email, pswd)==true){
            //create a user object
            User user = userDAO.getUser(email);
            //store the user object in the session
            httpSession.setAttribute("modirisi", user);

            url = "/BookControllerServlet";

        }else{
            message = "The password and/or email provided does not match our records\nPlease provide the correct email and password";
            url = "/login.jsp";
        }
        request.setAttribute("message",message);
        getServletContext().getRequestDispatcher(url).forward(request, response);

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}
