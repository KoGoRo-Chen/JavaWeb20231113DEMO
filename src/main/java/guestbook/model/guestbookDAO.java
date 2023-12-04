package guestbook.model;

import java.util.List;

/*
 * 定義訪客留言版
 * 新增與查詢
 */

public interface guestbookDAO {
	void create(Guestbook guestbook);
	List<Guestbook> readAll();
}
