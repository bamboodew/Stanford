package chap12;

import acm.graphics.*;
import acm.program.*;

@SuppressWarnings("serial")
public class Checkerboard extends GraphicsProgram {
	/* ���� */
	private static final int NROWS = 8;

	/* ���� */
	private static final int NCOLUMNS = 8;

	/* ���г��� */
	public void run() {
		this.setSize(800, 600); // ���ô��ڴ�С
		int sqSize = getHeight() / NROWS;
		println(getHeight() + " " + getWidth());
		for (int i = 0; i < NROWS; i++) {
			for (int j = 0; j < NCOLUMNS; j++) {
				int x = j * sqSize;
				int y = i * sqSize;
				GRect sq = new GRect(x, y, sqSize, sqSize); // ���巽��
				sq.setFilled(((i + j) % 2) == 0); // �ǣ���䡣�񣺲���䡣
				add(sq); // ��ӷ���
			}
		}
	}
}
