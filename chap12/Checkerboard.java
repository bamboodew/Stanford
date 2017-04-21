package chap12;

import acm.graphics.*;
import acm.program.*;

@SuppressWarnings("serial")
public class Checkerboard extends GraphicsProgram {
	/* 行数 */
	private static final int NROWS = 8;

	/* 列数 */
	private static final int NCOLUMNS = 8;

	/* 运行程序 */
	public void run() {
		this.setSize(800, 600); // 设置窗口大小
		int sqSize = getHeight() / NROWS;
		println(getHeight() + " " + getWidth());
		for (int i = 0; i < NROWS; i++) {
			for (int j = 0; j < NCOLUMNS; j++) {
				int x = j * sqSize;
				int y = i * sqSize;
				GRect sq = new GRect(x, y, sqSize, sqSize); // 定义方形
				sq.setFilled(((i + j) % 2) == 0); // 是：填充。否：不填充。
				add(sq); // 添加方形
			}
		}
	}
}
