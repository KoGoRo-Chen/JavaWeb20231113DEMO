package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

// 可以用來顯示今天日期與天氣
// 設計網址: http://localhost:8080/JavaWeb20231113/servlet/TodayServlet.java  這樣瀏覽器才知道要開啟這個servlet
@WebServlet(value = "/servlet/TodayServlet.java")
public class TodayServlet  extends GenericServlet{
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		//設定支援多國語系(utf-8)
		res.setCharacterEncoding("utf-8");  //給java使用
		res.setContentType("text/html;charset=utf-8");  //給瀏覽器使用
		//日期
		
		Date today = new Date(); // 這行程式碼使用 Date 類的預設建構函式，創建了一個表示當前日期和時間的 Date 物件。在 Java 中，Date 類的無參數建構函式會返回當前的日期和時間。
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss E");     //設定日期呈現格式sdf (年-月-日 上午/下午 小時(大寫為24小時制):分:秒 　星期幾)
		String todayString = sdf.format(today); //將目前時間(today)套入已經建構好的格式sdf中
		//天氣
		int temp = new Random().nextInt(10)+10;  //使用java.util.Random的Random()方法創造一個隨機的數值，並賦予給int變數temp
		//將結果傳給前端
		res.getWriter().println("今天: " + todayString + "</br>");    //使用ServletResponse中的getWriter的print方法，將今天日期傳送給客戶端。
		res.getWriter().println("氣溫: " + temp);    //使用ServletResponse中的getWriter的print方法，將今天日期傳送給客戶端。
	}
}

/* Java Date可使用LocalDateTime API來替代，寫法如下：
 * import java.time.LocalDateTime;
 * import java.time.format.DateTimeFormatter;
 * 
 * LocalDateTime now = LocalDateTime.now();
 * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss E");
 * String todayString = now.format(formatter);
*/