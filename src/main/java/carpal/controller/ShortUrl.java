package carpal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import carpal.bean.ShortUrlBean;
import carpal.dao.ShortUrlDao;

/**
 * Servlet implementation class ShortUrl
 */
public class ShortUrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShortUrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		StringBuilder ShortUrl =  new StringBuilder( request.getPathInfo().substring(1));
		System.out.println(ShortUrl.length());
		if(ShortUrl.length() > 6 ){
			System.out.println("Hello bossss");
			RequestDispatcher rd =  request.getRequestDispatcher("/home");
			rd.forward(request, response);	
		}
		else{
			String longurl = " ";
			 longurl  =  new ShortUrlDao().getLongUrl(ShortUrl);  
			 if(longurl.length() > 0){
				 response.sendRedirect(longurl); 
			 }else{
				// response.sendRedirect(request.getContextPath()+ "/index.jsp"); 
				 
				RequestDispatcher rd =  request.getRequestDispatcher("/home");
				rd.forward(request, response);
			 }
		}
		
		System.out.println( ShortUrl);
		//response.sendRedirect("https://stackoverflow.com/questions/24058117/how-to-make-servlet-map-a-special-url-to-a-static-resource");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	Enumeration parameter = request.getParameterNames();
	while(parameter.hasMoreElements()){
		System.out.println(parameter.nextElement());
	}
	
	HashMap<String, Object>  hashdata = new  HashMap<String, Object>();
		if(request.getParameter("long_url").length() > 0  && request.getParameter("oauthtoken").length() > 0 ) 
		{
		ShortUrlBean shortUrlBean =  new ShortUrlBean();
		shortUrlBean.setLong_url(request.getParameter("long_url"));
	//	shortUrlBean.setOauthtoken(request.getParameter("oauthtoken"));
		
		ShortUrlDao dao = new ShortUrlDao();
		hashdata =	dao.saveShortUrl(shortUrlBean);
			 
		}
		else{
			
			
			hashdata.put("issuccess", false);
			hashdata.put("message","Long url or Token is empty." );
		//	responsedata.add(hashdata);
		}
		
		 response.setContentType("application/json;charset=UTF-8");
		  PrintWriter out = response.getWriter();
		  String  resultJson = new Gson().toJson(hashdata);
		  
		  out.println(resultJson);
	}

	
	     
}
