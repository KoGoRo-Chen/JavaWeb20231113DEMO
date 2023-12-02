package filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// UTF-8 過濾器
/*這段代碼是一個過濾器（Filter），
 * 用於在處理所有 /guestbook/* 和 /group_buy/* 路徑下的請求之前，對請求進行預處理。
 * 主要的目的是確保處理這些請求的時候使用的字符編碼是UTF-8。
 */
@WebFilter(value = {"/guestbook/*", "/group_buy/*"})
public class UTF8EncodingFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		//res.getWriter().print("stop !");
		// 設定 utf-8
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		// 放行
		chain.doFilter(req, resp);
		
	}
	
}
