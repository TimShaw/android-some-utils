package com.airshiplay.framework.example.sliding;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SlidingDrawer;

import com.airshiplay.framework.R;
import com.airshiplay.framework.sliding.SlidingLayout;

/**
 * @author airshiplay
 * @Create Date 2013-3-7
 * @version 1.0
 * @since 1.0
 */
public class SlidingLayoutDemo extends Activity implements View.OnClickListener {
	private SlidingLayout mSlidingLayout;
	private View content;
	private View mSideBar;
	private View rightView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.slidinglayout);
		content = getWindow().findViewById(android.R.id.content);
		initContentView();
		setContentView();
	}

	void initContentView() {
		mSlidingLayout = (SlidingLayout) findViewById(R.id.slidinglayout);
		mSideBar = findViewById(R.id.sidebar);
		rightView = findViewById(R.id.manage_btn);
		//new SlidingDrawer(this,null);
	}

	void setContentView() {
		new SlidingHandler(this, mSlidingLayout);
		mSlidingLayout.firstShow();
		rightView.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		if (mSlidingLayout.isOpening()) {
			mSlidingLayout.toggleSidebar();
			return;
		}
		super.onBackPressed();
	}

	@Override
	public void onClick(View v) {
		mSlidingLayout.toggleSidebar();
	}
}
