package chap12;

import java.applet.Applet;
import java.awt.*;

@SuppressWarnings("serial")
public class DrawHeart extends Applet {
	int width, height;
	Image image; // ����������
	Graphics g1;

	public void init() // Applet��ʼ��ʱ����
	{
		this.setSize(800, 600);
		setBackground(Color.black); // ���ñ���
		this.setSize(300, 300);

		width = getSize().width; // ��ô��ڿ��
		height = getSize().height;
		image = createImage(width, height); // ����ͼ�����
		g1 = image.getGraphics();
	}

	public void paint(Graphics g) // ��ͼ����
	{
		g1.clearRect(0, 0, width, height);
		g1.setColor(Color.red);
		double r, x, y;
		// �ڻ�������������ͼ��
		for (int i = 0; i <= 90; i++) // ���ƺ���仯
			for (int j = 0; j <= 90; j++) // ��������仯
			{
				// ת��Ϊֱ������
				r = Math.PI / 45 * i * (1 - Math.sin(Math.PI / 45 * j)) * 18;
				x = r * Math.cos(Math.PI / 45 * j) * Math.sin(Math.PI / 45 * i) + width / 2; // Ϊ�����м���ʾ������ƫ����
				y = -r * Math.sin(Math.PI / 45 * j) + height / 4;
				// Ϊ�����м���ʾ������ƫ����
				g1.fillOval((int) x, (int) y, 2, 2); // ���Ƶ�
				// ��ʾ�������Ŀɱ�Image����
				g.drawImage(image, 0, 0, this);
			}
	}
}