package com.nix.flip;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class Scale3DAnimation extends Animation {

	private float mFromY;
	private float mFromX;
	private float mToY;
	private float mToX;
	private float centerY;
	private float centerX;

	/**
	 * @param mFromX 
	 * @param mToX
	 * @param mFromY
	 * @param mToY
	 */
	public Scale3DAnimation(float mFromX, float mToX, float mFromY, float mToY) {
		this.mFromX = mFromX;
		this.mFromY = mFromY;
		this.mToX = mToX;
		this.mToY = mToY;
		this.centerX = 0;
		this.centerY = 0;
	}

	/**
	 * 
	 * @param mFromX
	 * @param mToX
	 * @param mFromY
	 * @param mToY
	 * @param centerX 
	 * 			- center X point of the view
	 * @param centerY
	 * 			- center X point of the view
	 */
	public Scale3DAnimation(float mFromX, float mToX, float mFromY, float mToY,
			float centerX, float centerY) {
		// TODO Auto-generated constructor stub
		this.mFromX = mFromX;
		this.mFromY = mFromY;
		this.mToX = mToX;
		this.mToY = mToY;
		this.centerX = centerX;
		this.centerY = centerY;
	}

	@Override
	public void initialize(int width, int height, int parentWidth,
			int parentHeight) {
		// TODO Auto-generated method stub
		super.initialize(width, height, parentWidth, parentHeight);
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		super.applyTransformation(interpolatedTime, t);
		float sx = 1.0f, sy = 1.0f;

		if (mFromX != 1.0f || mToX != 1.0f) {
			sx = mFromX + ((mToX - mFromX) * interpolatedTime);
		}
		if (mFromY != 1.0f || mToY != 1.0f) {
			sy = mFromY + ((mToY - mFromY) * interpolatedTime);
		}
		if (centerX == 0 && centerY == 0) {
			t.getMatrix().setScale(sx, sy);
		} else {
			t.getMatrix().setScale(sx, sy, centerX, centerY);
		}

	}
}
