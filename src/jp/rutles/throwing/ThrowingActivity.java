/*
package jp.rutles.throwing;


import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.view.MenuItem.OnMenuItemClickListener;


public class ThrowingActivity extends Activity {
	public float disp_w, disp_h; // 画面サイズ
	private ThrowingView view; // 使用するビュークラス
	
    // Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowManager manager = window.getWindowManager();
        Display disp = manager.getDefaultDisplay();
        disp_w = disp.getWidth();
        disp_h = disp.getHeight();
        setContentView(R.layout.main);
        view = (ThrowingView) this.findViewById(R.id.ThrowingView01);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	MenuItem item1 = menu.add("スタート！");
    	OnMenuItemClickListener listener1 = new OnMenuItemClickListener() {
    		public boolean onMenuItemClick(MenuItem item) {
    			view.start();
    			return false;
    		}
    	};
    	item1.setOnMenuItemClickListener(listener1);
    	return true;
    }
}
*/

package jp.rutles.throwing;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.view.MenuItem.OnMenuItemClickListener;

public class ThrowingActivity extends Activity {
	public float disp_w, disp_h;
	private ThrowingView view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		WindowManager manager = window.getWindowManager();
		Display disp = manager.getDefaultDisplay();
		disp_w = disp.getWidth();
		disp_h = disp.getHeight();
		setContentView(R.layout.main);
		view = (ThrowingView) this.findViewById(R.id.ThrowingView01);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem item1 = menu.add("スタート！");
		OnMenuItemClickListener listener1 = new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				view.start();
				return false;
			}
		};
		item1.setOnMenuItemClickListener(listener1);
		return true;
	}
}