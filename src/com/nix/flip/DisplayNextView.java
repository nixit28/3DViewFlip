package com.nix.flip;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

public class DisplayNextView implements AnimationListener {

	private static final String TAG = DisplayNextView.class.getSimpleName();
	private boolean mCurrentView;
	TextView text1;
	TextView text2;

	public DisplayNextView(boolean currentView, TextView text1,
			TextView text2) {
		mCurrentView = currentView;
		this.text1 = text1;
		this.text2 = text2;
	}

	public void onAnimationStart(Animation animation) {
		Log.i(TAG, "Animation Start...");
	}

	public void onAnimationEnd(Animation animation) {
		Log.i(TAG, "Animation End...");
		text1.post(new SwapViews(mCurrentView, text1, text2));
	}

	public void onAnimationRepeat(Animation animation) {
		Log.i(TAG, "Animation Repeat...");
	}
}