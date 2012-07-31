/*
package jp.rutles.throwing;


import java.util.concurrent.*;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.*;
import android.view.*;
import android.widget.Toast;

public class ThrowingView extends SurfaceView 
       implements SurfaceHolder.Callback {
	private static final long GAME_TIMES = 30000; // プレイ時間（ミリ秒）
	private ScheduledExecutorService executor; // スレッド用インスタンス
	private SurfaceHolder holder; // サーフェイスのホルダ
	private ThrowingActivity throwing; // アクティビティクラス
	private Enemies enemies; // 敵管理クラス
	private Chara chara; // キャラクタ管理クラス
	private Ball ball; // ボール管理クラス
	private Drawable back; // あい系イメージ
	private float score_x = 40; // スコアの横位置
	private float score_y = 40; // スコアの縦位置
	private float char_x = 100; // キャラクタの横位置
	private float char_y = 350; // キャラクタの縦位置
	private float enemy_x = 400; // 敵キャラの横位置
	private float enemy_y = 50; // 敵キャラの縦位置
	private float end_x = 200; // 終了表示の横位置
	private float end_y = 200; // 終了表示の縦位置
	private int score = 0; // スコア
	private boolean game_end = true; // ゲーム終了チェック
	private long start_time; // ゲーム開始時間
	private Point press_loc; // タッチした場所の保管
	
	public ThrowingView(Context context) {
		super(context);
		init(context);
	}
	
	public ThrowingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public void init(Context context) {
		holder = getHolder();
		holder.addCallback(this);
		setFocusable(true);
		requestFocus();
		throwing = (ThrowingActivity) context;
		setBoardSize();
	}
	
	private void setBoardSize() {
		float w = throwing.disp_w;
		float h = throwing.disp_h;
		float dw = w / 800f;
		float dh = h / 480f;
		
		score_x *= dw;
		score_y *= dh;
		char_x *= dw;
		char_y *= dh;
		enemy_x *= dw;
		enemy_y *= dh;
		end_x *= dw;
		end_y *= dh;
		
		Resources resources = throwing.getResources();
		back = resources.getDrawable(R.drawable.back);
		back.setBounds(new Rect(0, 0, (int)throwing.disp_w, (int) throwing.disp_h));
		Drawable ball_img = resources.getDrawable(R.drawable.ball);
		ball = new Ball(ball_img, char_x, char_y);
		Drawable bang_img = resources.getDrawable(R.drawable.bang);
		Drawable[] char_img = new Drawable[3];
		char_img[0] = resources.getDrawable(R.drawable.char1);
		char_img[1] = resources.getDrawable(R.drawable.char2);
		char_img[2] = resources.getDrawable(R.drawable.char3);
		chara = new Chara(char_img, char_x, char_y);
		Drawable[] enemy_img = new Drawable[4];
		enemy_img[0] = resources.getDrawable(R.drawable.enemy1);
		enemy_img[1] = resources.getDrawable(R.drawable.enemy2);
		enemy_img[2] = resources.getDrawable(R.drawable.enemy3);
		enemy_img[3] = enemy_img[2];
		enemies = new Enemies(enemy_img, bang_img, enemy_x, enemy_y);
	}
	
	public void start() {
		try {
			executor.shutdown();
		} catch (Exception e) {}
		enemies.init();
		chara.init();
		ball.init();
		score = 0;
		start_time = SystemClock.currentThreadTimeMillis();
		game_end = false;
		executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				enemies.move();
				chara.move();
				ball.move();
				if (ball.isFlying())
					score += enemies.checkHit(ball.getCenter()) * 10;
				if (enemies.isFinished()) {
					gameOver();
				}
				if (SystemClock.currentThreadTimeMillis() - start_time > GAME_TIMES) {
					gameOver();
				}
				draw();
			}
		}, 100, 100, TimeUnit.MILLISECONDS);
		showMsg("スタート！");
	}
	
	public void showMsg(String s) {
		Toast toast = Toast.makeText(throwing, s, Toast.LENGTH_SHORT);
		toast.show();
	}
	
	public void gameOver() {
		game_end = true;
		executor.shutdown();
		draw();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (game_end) return true;
		int n = event.getAction();
		float x = event.getX();
		float y = event.getY();
		switch (n) {
		case MotionEvent.ACTION_DOWN:
			press_loc = new Point((int) x, (int) y);
			break;
		case MotionEvent.ACTION_UP:
			float dy = x - press_loc.x;
			float dx = press_loc.y - y;
			chara.throwing();
			ball.flying(new Point((int) dx, (int) dy));
			score--;
			if (score < 0) score = 0;
			break;
		}
		return true;
	}
	
	public void draw() {
		Canvas canvas = holder.lockCanvas();
		back.draw(canvas);
		chara.draw(canvas);
		enemies.draw(canvas);
		ball.draw(canvas);
		Paint p = new Paint();
		p.setTextSize(30);
		p.setFakeBoldText(true);
		canvas.drawText("SCORE: " + score, score_x, score_y, p);
		if (game_end) {
			Paint p2 = new Paint();
			p2.setTextSize(70);
			p2.setFakeBoldText(true);
			p2.setColor(Color.RED);
			canvas.drawText("GAME OVER.", end_x, end_y, p2);
		}
		holder.unlockCanvasAndPost(canvas);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		draw();
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		executor.shutdown();
	}
}
*/

package jp.rutles.throwing;

import java.util.concurrent.*;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.*;
import android.view.*;
import android.widget.Toast;

public class ThrowingView extends SurfaceView
		implements SurfaceHolder.Callback {
	private static final long GAME_TIMES = 30000; // ÉvÉåÉCéûä‘ÅiÉ~ÉäïbÅj
	private ScheduledExecutorService executor; // ÉXÉåÉbÉhópÉCÉìÉXÉ^ÉìÉX
	private SurfaceHolder holder; // ÉTÅ[ÉtÉFÉCÉXÇÃÉzÉãÉ_
	private ThrowingActivity throwing; // ÉAÉNÉeÉBÉrÉeÉBÉNÉâÉX
	private Enemies enemies; // ìGä«óùÉNÉâÉX
	private Chara chara; // ÉLÉÉÉâÉNÉ^ä«óùÉNÉâÉX
	private Ball ball; // É{Å[Éãä«óùÉNÉâÉX
	private Drawable back; // Ç†Ç¢ånÉCÉÅÅ[ÉW
	private float score_x = 40; // ÉXÉRÉAÇÃâ°à íu
	private float score_y = 40; // ÉXÉRÉAÇÃècà íu
	private float char_x = 100; // ÉLÉÉÉâÉNÉ^ÇÃâ°à íu
	private float char_y = 350; // ÉLÉÉÉâÉNÉ^ÇÃècà íu
	private float enemy_x = 400; // ìGÉLÉÉÉâÇÃâ°à íu
	private float enemy_y = 50; // ìGÉLÉÉÉâÇÃècà íu
	private float end_x = 200; // èIóπï\é¶ÇÃâ°à íu
	private float end_y = 200; // èIóπï\é¶ÇÃècà íu
	private int score = 0; // ÉXÉRÉA
	private boolean game_end = true; // ÉQÅ[ÉÄèIóπÉ`ÉFÉbÉN
	private long start_time; // ÉQÅ[ÉÄäJénéûä‘
	private Point press_loc; // É^ÉbÉ`ÇµÇΩèÍèäÇÃï€ä«

	public ThrowingView(Context context) {
		super(context);
		init(context);
	}

	public ThrowingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public void init(Context context) {
		holder = getHolder();
		holder.addCallback(this);
		setFocusable(true);
		requestFocus();
		// setFocusableInTouchMode(true);
		throwing = (ThrowingActivity) context;
		setBoardSize();
	}

	private void setBoardSize() {
		float w = throwing.disp_w;
		float h = throwing.disp_h;
		float dw = w / 854f;
		float dh = h / 480f;

		score_x *= dw;
		score_y *= dh;
		char_x *= dw;
		char_y *= dh;
		enemy_x *= dw;
		enemy_y *= dh;
		end_x *= dw;
		end_y *= dh;

		Resources resources = throwing.getResources();
		back = resources.getDrawable(R.drawable.back);
		back.setBounds(new Rect(0, 0, (int) throwing.disp_w,
				(int) throwing.disp_h));
		Drawable ball_img = resources.getDrawable(R.drawable.ball);
		ball = new Ball(ball_img, char_x, char_y);
		Drawable bang_img = resources.getDrawable(R.drawable.bang);
		Drawable[] char_img = new Drawable[3];
		char_img[0] = resources.getDrawable(R.drawable.char1);
		char_img[1] = resources.getDrawable(R.drawable.char2);
		char_img[2] = resources.getDrawable(R.drawable.char3);
		chara = new Chara(char_img, char_x, char_y);
		Drawable[] enemy_img = new Drawable[4];
		enemy_img[0] = resources.getDrawable(R.drawable.enemy1);
		enemy_img[1] = resources.getDrawable(R.drawable.enemy2);
		enemy_img[2] = resources.getDrawable(R.drawable.enemy3);
		enemy_img[3] = enemy_img[2];
		enemies = new Enemies(enemy_img, bang_img, enemy_x, enemy_y);
	}

	public void start() {
		try {
			executor.shutdown();
		} catch (Exception e) {}
		enemies.init();
		chara.init();
		ball.init();
		score = 0;
		start_time = SystemClock.currentThreadTimeMillis();
		game_end = false;
		executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				enemies.move();
				chara.move();
				ball.move();
				if (ball.isFlying())
					score += enemies.checkHit(ball.getCenter()) * 10;
				if (enemies.isFinished()) {
					gameOver();
				}
				if (SystemClock.currentThreadTimeMillis() - start_time > GAME_TIMES) {
					gameOver();
				}
				draw();
			}
		}, 100, 100, TimeUnit.MILLISECONDS);
		showMsg("スタート!");
	}

	public void showMsg(String s) {
		Toast toast = Toast.makeText(throwing, s, Toast.LENGTH_SHORT);
		toast.show();
	}

	public void gameOver() {
		game_end = true;
		try {
			executor.shutdown();
		} catch (Exception e) {}
		draw();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (game_end) return true;
		int n = event.getAction();
		float x = event.getX();
		float y = event.getY();
		switch (n) {
		case MotionEvent.ACTION_DOWN:
			press_loc = new Point((int) x, (int) y);
			break;
		case MotionEvent.ACTION_UP:
			float dy = x - press_loc.x;
			float dx = press_loc.y - y;
			chara.throwing();
			ball.flying(new Point((int) dx, (int) dy));
			score--;
			if (score < 0) score = 0;
			break;
		}
		return true;
	}

	public void draw() {
		Canvas canvas = holder.lockCanvas();
		back.draw(canvas);
		chara.draw(canvas);
		enemies.draw(canvas);
		ball.draw(canvas);
		Paint p = new Paint();
		p.setTextSize(30);
		p.setFakeBoldText(true);
		canvas.drawText("SCORE: " + score, score_x, score_y, p);
		if (game_end) {
			Paint p2 = new Paint();
			p2.setTextSize(70);
			p2.setFakeBoldText(true);
			p2.setColor(Color.RED);
			canvas.drawText("GAME OVER.", end_x, end_y, p2);
		}
		holder.unlockCanvasAndPost(canvas);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		draw();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		executor.shutdown();
	}

}
