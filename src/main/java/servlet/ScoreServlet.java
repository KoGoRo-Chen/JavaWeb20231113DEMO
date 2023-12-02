package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.IntSummaryStatistics;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// score?score=100&score=45&score=80
@WebServlet(value = "/servlet/Score")
public class ScoreServlet extends HttpServlet {

	//
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		// 接收客戶端來的請求參數
		String[] scores = req.getParameterValues("score");
		
		// 成績筆數 = ? 平均 = ? 總分 = ? 最高分 = ? 最低分 = ? 
		Map<String, Number> scoreInfo = getScoreInfo(scores);
		//resp.getWriter().print(scoreInfo);
		
		// 將 map 資料丟給 jsp 進行資料渲染
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/score_result.jsp");
		req.setAttribute("scoreInfo", scoreInfo); // 將要傳遞給 jsp 的資料放到 req 的物件屬性中
		req.setAttribute("scores", scores);
		req.setAttribute("redColor", "red");
		req.setAttribute("blueColor", "blue");
		rd.forward(req, resp);
		
	}
	
	// Map語法 key是成績筆數 value就是成績
	private Map<String, Number> getScoreInfo(String[] scores) {
		if (scores == null) {
			return null;
		}
		IntSummaryStatistics stat = Arrays.stream(scores) // 字串陣列流
				//.mapToInt(str -> Integer.parseInt(str)) // int 陣列串流
				.mapToInt(Integer::parseInt) // int 陣列串流
				.summaryStatistics();
		
		Map<String, Number> map = new HashMap<>();
		map.put("count", stat.getCount());
		map.put("average", stat.getAverage());
		map.put("sum", stat.getSum());
		map.put("max", stat.getMax());
		map.put("int", stat.getMin());
		return map;

	}
}

/*
 * //印出所有分數 PrintWriter out = resp.getWriter();
 * //先創建PrintWriter變數out，賦予resp.getWriter() 之後就能直接以out呼叫getWriter();
 * Arrays.stream(scores).forEach(score -> out.print(score));
 * Arrays.stream(scores).forEach(out::print); //方法3 方法參考
 * out.println("分數: " + Arrays.toString(scores) + "<p>");
 * 
 * 分析陣列數字 將score[]轉型成int int[] scoresInt = new int[scores.length]; for(int i =
 * 0; i < scores.length; i++) { scoresInt[i] = Integer.parseInt(scores[i]); }
 * Arrays.stream(scoresInt).forEach(score -> out.print(score + "<br>")); }
 */

/*
 * getRequestDispatcher 是HttpServletRequest 接口中的方法，用於獲取用於控制轉發的 RequestDispatcher 物件。
 * 控制轉發是一種在同一應用程序內將請求轉發到另一個資源（如Servlet、JSP或HTML頁面）的機制。
 * RequestDispatcher rd = req.getRequestDispatcher("/path/to/resource");
 * 這個方法接收一個路徑參數，該路徑可以是相對於當前 Servlet 上下文的路徑。在上述的例子中，"/path/to/resource" 是要轉發的資源的路徑。
 * 控制轉發與重定向（Redirect）不同，控制轉發是在伺服器內部進行的，客戶端對此一無所知，URL 不會發生改變。
 * 這意味著控制轉發的內部流程是在伺服器內完成的，而不需要客戶端的介入。 
 */