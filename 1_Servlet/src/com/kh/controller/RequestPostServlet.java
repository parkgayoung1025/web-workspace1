package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do")
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("잘 실행되니..?");
		
		// 요청 시 전달된 값들은 request parameter 영역에 담겨있음
		// post 방식 요청 같은 경우는 값을 뽑기 전에 반드시 UTF-8방식으로 인코딩 설정 해야 함(post 방식의 기본값 ISO-8859-1)
				
		request.setCharacterEncoding("UTF-8");
				
		String name = request.getParameter("name");// 텍스트 박스가 비어있는 경우 빈 문자열("")로 넘어온다
		String gender = request.getParameter("gender");// 라디오 버튼의 경우 체크된 값이 없을 때 빈 문자열이 아닌 null이 넘어간다
		int age = Integer.parseInt(request.getParameter("age")); // 반환값이 무조건 문자열이기 때문에 형 변환해 줘야 함.
				
		String city = request.getParameter("city");
		double height = Double.parseDouble(request.getParameter("height"));// "160" -> 160.0
				
		//체크박스처럼 복수개의 정보를 받을 때는 배열 형태로 받아야 함.
		String[] foods = request.getParameterValues("food");
				
		// 요청 처리 : Service -> DAO - SQL문
				
		// 위의 요청 처리를 다 했다는 가정하에 사용자가 보게 될 응답 페이지 출력
				
		// 순수 Servlet 방식 : Java 소스코드 내에 HTML을 작성
		// JSP(Java Server Page) : html 내에 Jav a코드를 쓸 수 있는 기술
				
		// 응답 페이지를 만드는 과정을 jsp에게 위임
				
		// 단, 그 응답 화면(jsp)에서 필요로 하는 데이터들을 request 객체에 담아서 보내줘야 함
		// request의 attribute 영역에 담아서 보내줄 예정(key, value 세트로)
		request.setAttribute("name", name);
		request.setAttribute("age", age);
		request.setAttribute("gender", gender);
		request.setAttribute("city", city);
		request.setAttribute("height", height);
		request.setAttribute("foods", foods);
				
		// 위임 시 필요한 객체 : RequestDispatcher
		// 1) 응답하고자 하는 뷰(jsp)를 선택하면서 생성
		RequestDispatcher view = request.getRequestDispatcher("views/responsePage.jsp");
		// /servlet/은 항상 자동으로 들어가 있음
				
		// 2) 포워딩
		view.forward(request, response);
	}

}
