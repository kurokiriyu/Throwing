/*　リスト５－１２
package jp.rutles.throwing;

import android.graphics.*;
import android.graphics.drawable.Drawable;

public class Enemies {
	private static final int COL = 5; // 横の数
	private static final int ROW = 5; // 縦の数
	private static final int HIT_DATA = -1; // ヒットしたときを示す番号
	private static final int NO_DATA = -5; // 何も表示しない状態を示す番号
	private float enemy_x = 500; // 横位置
	private float enemy_y = 50; // 縦位置
	private float enemy_w = 50; // 横幅
	private float enemy_h = 50; // 縦幅
	private float space_w = 80; // 各敵キャラの間隔（横）
	private float space_h = 70; // 各敵キャラの間隔（縦）
	private int[][] datas; // 表示Drawableを管理する２次元配列
	private Drawable[] imgs; // 表示用Drawableを管理する配列
	private Drawable bang_img; // 衝突のグラフィック
	
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

リスト５－１２*/


// ここから５－１３
	package jp.rutles.throwing;

	import java.util.Random;	// リスト5-13より追加

	import android.graphics.*;
	import android.graphics.drawable.Drawable;
	import android.os.SystemClock;	// リスト5-13より追加

	public class Enemies {
		private static final int COL = 5; // 横の数
		private static final int ROW = 5; // 縦の数
		private static final int HIT_DATA = -1; // ヒットしたときを示す番号
		private static final int NO_DATA = -5; // 何も表示しない状態を示す番号
		private float enemy_x = 500; // 横位置
		private float enemy_y = 50; // 縦位置
		private float enemy_w = 50; // 横幅
		private float enemy_h = 50; // 縦幅
		private float space_w = 80; // 各敵キャラの間隔（横）
		private float space_h = 70; // 各敵キャラの間隔（縦）
		private Enemy[][] datas; // 表示Drawableを管理する２次元配列
		private Drawable[] imgs; // 表示用Drawableを管理する配列
		private Drawable bang_img; // 衝突のグラフィック
		
		public Enemies(Drawable[] imgs, Drawable bang_img, float x, float y) {
			this.imgs = imgs;
			this.bang_img = bang_img;
			enemy_x = x;
			enemy_y = y;
		}
		
		public void init() {
			int n = 0;	// リスト5-13より追加
			datas = new Enemy[COL][ROW];	// リスト5-13より追加
			Random rnd = new Random(SystemClock.currentThreadTimeMillis());	//// リスト5-13より追加
			for (int i = 0; i < COL; i++) {
				for (int j = 0; j < ROW; j++) {
					datas[i][j] = new Enemy(	// リスト5-13より追加
							n++ + 1 % 3,	// リスト5-13より追加
							enemy_x + space_w * i,	// リスト5-13より追加
							enemy_y + space_h * j,	// リスト5-13より追加
							rnd.nextFloat() * 5 - 2.5f,	// リスト5-13より追加
							rnd.nextFloat() * 5 - 2.5f);	// リスト5-13より追加
				}
			}
		}
		
		public void move() {
			for (int i = 0; i < COL; i++) {
				for (int j = 0; j < ROW; j++) {
					if (datas[i][j].show_img >= 0){			// リスト5-13より追加		
						datas[i][j].show_img = (datas[i][j].show_img + 1) % 4;	// リスト5-13より追加
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
				for (int j = 0; j < ROW; j++) {	// p.267～
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
// ここまで本の５－１３
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