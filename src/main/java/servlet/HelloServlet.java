package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/servlet/HelloServlet.java")
public class HelloServlet extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//設定支援多國語系(utf-8)
		resp.setCharacterEncoding("utf-8");  //給java使用
		resp.setContentType("text/html;charset=utf-8");  //給瀏覽器使用
		
		String name = req.getParameter("name"); //取得網址列上請求的name參數	
		String age = req.getParameter("age"); //取得網址列上請求的name參數	
		PrintWriter out = resp.getWriter(); //先創建PrintWriter變數out，賦予resp.getWriter() 之後就能直接以out呼叫getWriter();
		out.println("哈囉！" + name + "<p>");
		out.print("年紀" + age);
	}
	
}

//	CRUD = Create Read Update Delete