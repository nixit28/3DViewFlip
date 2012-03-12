package com.nix.flip;

import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

public class SwapViews implements Runnable {

	private boolean mIsFirstView;
	TextView text1;
	TextView text2;

	public SwapViews(boolean isFirstView, TextView text1, TextView text2) {
		mIsFirstView = isFirstView;
		this.text1 = text1;
		this.text2 = text2;
	}

	public void run() {
		final float centerX = text1.getWidth() / 2.0f;
		final float centerY = text1.getHeight() / 2.0f;
		Flip3dAnimation rotation;
		AnimationSet animSet = new AnimationSet(false);

		Scale3DAnimation zoom = new Scale3DAnimation(0.7f, 1.0f, 0.7f, 1.0f);
//		Scale3DAnimation zoom = new Scale3DAnimation(0.7f, 1.0f, 0.7f, 1.0f, centerX, centerY);
		if (mIsFirstView) {
			text1.setVisibility(View.GONE);
			text2.setVisibility(View.VISIBLE);
			text2.requestFocus();

			rotation = new Flip3dAnimation(-90, 0, centerX, centerY);
		} else {
			text2.setVisibility(View.GONE);
			text1.setVisibility(View.VISIBLE);
			text1.requestFocus();

			rotation = new Flip3dAnimation(90, 0, centerX, centerY);
		}

		rotation.setDuration(600);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new DecelerateInterpolator());
		
		zoom.setDuration(600);
		zoom.setInterpolator(new DecelerateInterpolator());
		zoom.setFillAfter(true);
		
		animSet.addAnimation(rotation);
		animSet.addAnimation(zoom);
		
		

		if (mIsFirstView) {
			text2.startAnimation(animSet);
		} else {
			text1.startAnimation(animSet);
		}
	}
}