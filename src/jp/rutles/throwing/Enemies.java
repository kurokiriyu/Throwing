/*�@���X�g�T�|�P�Q
package jp.rutles.throwing;

import android.graphics.*;
import android.graphics.drawable.Drawable;

public class Enemies {
	private static final int COL = 5; // ���̐�
	private static final int ROW = 5; // �c�̐�
	private static final int HIT_DATA = -1; // �q�b�g�����Ƃ��������ԍ�
	private static final int NO_DATA = -5; // �����\�����Ȃ���Ԃ������ԍ�
	private float enemy_x = 500; // ���ʒu
	private float enemy_y = 50; // �c�ʒu
	private float enemy_w = 50; // ����
	private float enemy_h = 50; // �c��
	private float space_w = 80; // �e�G�L�����̊Ԋu�i���j
	private float space_h = 70; // �e�G�L�����̊Ԋu�i�c�j
	private int[][] datas; // �\��Drawable���Ǘ�����Q�����z��
	private Drawable[] imgs; // �\���pDrawable���Ǘ�����z��
	private Drawable bang_img; // �Փ˂̃O���t�B�b�N
	
	public Enemies(Drawable[] imgs, Drawable bang_img, float x, float y) {
		this.imgs = imgs;
		this.bang_img = bang_img;
		datas = new int[COL][ROW];
		enemy_x = x;
		enemy_y = y;
	}
	
	public void init() {
		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {
				datas[i][j] = 0;
			}
		}
	}
	
	public void move() {
		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {
				if (datas[i][j] >= 0)	
					datas[i][j] = (datas[i][j] + 1) % 4;
					datas[i][j].x += datas[i][j].dx;
					if (datas[i][j].x < enemy_x - enemy_w)
						datas[i][j].dx *= -1;
					if (datas[i][j].x > enemy_x + COL * space_w + enemy_w)
						datas[i][j].dx *= -1;
					datas[i][j].y += datas[i][j].dy;
					datas[i][j].
					datas[i][j].
			}
		}
	}
	
	public int checkHit(Point p) {
		int count = 0;
		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {
				if (datas[i][j] < 0)
					continue;
				float x1 = enemy_x + space_w * i;
				float y1 = enemy_y + space_h * j;
				float x2 = x1 + enemy_w;
				float y2 = y1 + enemy_h;
				if (p.x > x1 && p.x < x2 && p.y > y1 && p.y < y2) {
					datas[i][j] = HIT_DATA;
					count++;
				}
			}
		}
		return count;
	}
	
	public boolean isFinished() {
		boolean f = true;
		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {
				if (datas[i][j] >= 0) f = false;
			}
		}
		return f;
	}
	
	public void draw(Canvas canvas) {
		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {
				int n = datas[i][j];
				Rect r = new Rect((int) (enemy_x + space_w * i),
						(int) (enemy_y + space_h * j), (int) (enemy_x + space_w * i + enemy_w),
						(int) (enemy_y + space_h * j + enemy_h));
				if (n >= 0) {
					imgs[n].setBounds(r);
					imgs[n].draw(canvas);
				} else if (n < 0) {
					if (n != NO_DATA) {
						datas[i][j] -= 1;
						bang_img.setBounds(r);
						bang_img.draw(canvas);
					}
				}
			}
		}
	}
}

���X�g�T�|�P�Q*/


// ��������T�|�P�R
	package jp.rutles.throwing;

	import java.util.Random;	// ���X�g5-13���ǉ�

	import android.graphics.*;
	import android.graphics.drawable.Drawable;
	import android.os.SystemClock;	// ���X�g5-13���ǉ�

	public class Enemies {
		private static final int COL = 5; // ���̐�
		private static final int ROW = 5; // �c�̐�
		private static final int HIT_DATA = -1; // �q�b�g�����Ƃ��������ԍ�
		private static final int NO_DATA = -5; // �����\�����Ȃ���Ԃ������ԍ�
		private float enemy_x = 500; // ���ʒu
		private float enemy_y = 50; // �c�ʒu
		private float enemy_w = 50; // ����
		private float enemy_h = 50; // �c��
		private float space_w = 80; // �e�G�L�����̊Ԋu�i���j
		private float space_h = 70; // �e�G�L�����̊Ԋu�i�c�j
		private Enemy[][] datas; // �\��Drawable���Ǘ�����Q�����z��
		private Drawable[] imgs; // �\���pDrawable���Ǘ�����z��
		private Drawable bang_img; // �Փ˂̃O���t�B�b�N
		
		public Enemies(Drawable[] imgs, Drawable bang_img, float x, float y) {
			this.imgs = imgs;
			this.bang_img = bang_img;
			enemy_x = x;
			enemy_y = y;
		}
		
		public void init() {
			int n = 0;	// ���X�g5-13���ǉ�
			datas = new Enemy[COL][ROW];	// ���X�g5-13���ǉ�
			Random rnd = new Random(SystemClock.currentThreadTimeMillis());	//// ���X�g5-13���ǉ�
			for (int i = 0; i < COL; i++) {
				for (int j = 0; j < ROW; j++) {
					datas[i][j] = new Enemy(	// ���X�g5-13���ǉ�
							n++ + 1 % 3,	// ���X�g5-13���ǉ�
							enemy_x + space_w * i,	// ���X�g5-13���ǉ�
							enemy_y + space_h * j,	// ���X�g5-13���ǉ�
							rnd.nextFloat() * 5 - 2.5f,	// ���X�g5-13���ǉ�
							rnd.nextFloat() * 5 - 2.5f);	// ���X�g5-13���ǉ�
				}
			}
		}
		
		public void move() {
			for (int i = 0; i < COL; i++) {
				for (int j = 0; j < ROW; j++) {
					if (datas[i][j].show_img >= 0){			// ���X�g5-13���ǉ�		
						datas[i][j].show_img = (datas[i][j].show_img + 1) % 4;	// ���X�g5-13���ǉ�
						datas[i][j].x += datas[i][j].dx;
						if (datas[i][j].x < enemy_x - enemy_w)
							datas[i][j].dx *= -1;
						if (datas[i][j].x > enemy_x + COL * space_w + enemy_w)
							datas[i][j].dx *= -1;
						datas[i][j].y += datas[i][j].dy;
						if (datas[i][j].y < enemy_y - enemy_h)
							datas[i][j].dy *= -1;
						if (datas[i][j].y > enemy_y + ROW * space_h + enemy_h)
							datas[i][j].dy *= -1;
					}						
				}
			}
		}
		
		public int checkHit(Point p) {
			int count = 0;
			for (int i = 0; i < COL; i++) {
				for (int j = 0; j < ROW; j++) {	// p.267�`
					if (datas[i][j].show_img < 0)
						continue;
					float x1 = datas[i][j].x;
					float y1 = datas[i][j].y;
					float x2 = x1 + enemy_w;
					float y2 = y1 + enemy_h;
					if (p.x > x1 && p.x < x2 && p.y > y1 && p.y < y2) {
						datas[i][j].show_img = HIT_DATA;
						count++;
					}
				}
			}
			return count;
		}
		
		public boolean isFinished() {
			boolean f = true;
			for (int i = 0; i < COL; i++) {
				for (int j = 0; j < ROW; j++) {
					if (datas[i][j].show_img >= 0) f = false;
				}
			}
			return f;
		}
		
		public void draw(Canvas canvas) {
			if (datas == null) return;
			for (int i = 0; i < COL; i++) {
				for (int j = 0; j < ROW; j++) {
					int n = datas[i][j].show_img;
					Rect r = new Rect((int) datas[i][j].x,
							(int) datas[i][j].y,
							(int) (datas[i][j].x + enemy_w),
							(int) (datas[i][j].y + enemy_h));
					if (n >= 0) {
						imgs[n].setBounds(r);
						imgs[n].draw(canvas);
					} else if (n < 0) {
						if (n != NO_DATA) {
							datas[i][j].show_img -= 1;
							bang_img.setBounds(r);
							bang_img.draw(canvas);
						}
					}
				}
			}
		}
	}
	
	class Enemy {
		public int show_img;
		public float x, y, dx, dy;
		
		public Enemy(int img, float x, float y, float dx, float dy) {
			this.show_img = img;
			this.x = x;
			this.y = y;
			this.dx = dx;
			this.dy = dy;
		}
	}
// �����܂Ŗ{�̂T�|�P�R
/*
	package jp.rutles.throwing;

	import java.util.Random;

	import android.graphics.*;
	import android.graphics.drawable.Drawable;
	import android.os.SystemClock;

	public class Enemies {
		private static final int COL = 5;
		private static final int ROW = 5;
		private static final int HIT_DATA = -1;
		private static final int NO_DATA = -5;
		private float enemy_x = 500;
		private float enemy_y = 50;
		private float enemy_w = 50;
		private float enemy_h = 50;
		private float space_w = 80;
		private float space_h = 70;
		private Enemy[][] datas;
		private Drawable[] imgs;
		private Drawable bang_img;

		public Enemies(Drawable[] imgs, Drawable bang_img, float x, float y) {
			this.imgs = imgs;
			this.bang_img = bang_img;
			enemy_x = x;
			enemy_y = y;
		}

		public void init() {
			int n = 0;
			datas = new Enemy[COL][ROW];
			Random rnd = new Random(SystemClock.currentThreadTimeMillis());
			for (int i = 0; i < COL; i++) {
				for (int j = 0; j < ROW; j++) {
					datas[i][j] = new Enemy(
							n++ + 1 % 3,
							enemy_x + space_w * i,
							enemy_y + space_h * j,
							rnd.nextFloat() * 5 - 2.5f,
							rnd.nextFloat() * 5 - 2.5f);
				}
			}
		}

		public void move() {
			for (int i = 0; i < COL; i++) {
				for (int j = 0; j < ROW; j++) {
					if (datas[i][j].show_img >= 0){
						datas[i][j].show_img = (datas[i][j].show_img + 1) % 4;
						datas[i][j].x += datas[i][j].dx;
						if (datas[i][j].x < enemy_x - enemy_w)
							datas[i][j].dx *= -1;
						if (datas[i][j].x > enemy_x + COL * space_w + enemy_w)
							datas[i][j].dx *= -1;
						datas[i][j].y += datas[i][j].dy;
						if (datas[i][j].y < enemy_y - enemy_h)
							datas[i][j].dy *= -1;
						if (datas[i][j].y > enemy_y + ROW * space_h + enemy_h)
							datas[i][j].dy *= -1;
					}
				}
			}
		}

		public int checkHit(Point p) {
			int count = 0;
			for (int i = 0; i < COL; i++) {
				for (int j = 0; j < ROW; j++) {
					if (datas[i][j].show_img < 0)
						continue;
					float x1 = datas[i][j].x;
					float y1 = datas[i][j].y;
					float x2 = x1 + enemy_w;
					float y2 = y1 + enemy_h;
					if (p.x > x1 && p.x < x2 && p.y > y1 && p.y < y2) {
						datas[i][j].show_img = HIT_DATA;
						count++;
					}
				}
			}
			return count;
		}

		public boolean isFinished() {
			boolean f = true;
			for (int i = 0; i < COL; i++) {
				for (int j = 0; j < ROW; j++) {
					if (datas[i][j].show_img >= 0) f = false;
				}
			}
			return f;
		}

		public void draw(Canvas canvas) {
			if (datas == null) return;
			for (int i = 0; i < COL; i++) {
				for (int j = 0; j < ROW; j++) {
					int n = datas[i][j].show_img;
					Rect r = new Rect((int) datas[i][j].x,
							(int) datas[i][j].y,
							(int) (datas[i][j].x + enemy_w),
							(int) (datas[i][j].y + enemy_h));
					if (n >= 0) {
						imgs[n].setBounds(r);
						imgs[n].draw(canvas);
					} else if (n < 0) {
						if (n != NO_DATA) {
							datas[i][j].show_img -= 1;
							bang_img.setBounds(r);
							bang_img.draw(canvas);
						}
					}
				}
			}
		}
	}

	class Enemy {
		public int show_img;
		public float x, y, dx, dy;
		
		public Enemy(int img, float x, float y, float dx, float dy){
			this.show_img = img;
			this.x = x;
			this.y = y;
			this.dx = dx;
			this.dy = dy;
		}
	}
*/