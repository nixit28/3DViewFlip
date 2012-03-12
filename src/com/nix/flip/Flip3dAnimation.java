package com.nix.flip;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;
public class Flip3dAnimation extends Animation {

	private final float mFromDegrees;
	private final float mToDegrees;
	private final float mCenterX;
	private final float mCenterY;
	private Camera mCamera;
	@SuppressWarnings("unused")
	private float scaleY;
	@SuppressWarnings("unused")
	private float scaleX;

	public Flip3dAnimation(float fromDegrees, float toDegrees, float centerX,
			float centerY) {
		mFromDegrees = fromDegrees;
		mToDegrees = toDegrees;
		mCenterX = centerX;
		mCenterY = centerY;
	}

//	public Flip3dAnimation(float fromDegrees, float toDegrees, float centerX,
//			float centerY, float sx, float sy) {
//
//		mFromDegrees = fromDegrees;
//		mToDegrees = toDegrees;
//		mCenterX = centerX;
//		mCenterY = centerY;
//		scaleX = sx;
//		scaleY = sy;
//	}

	@Override
	public void initialize(int width, int height, int parentWidth,
			int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		mCamera = new Camera();
	}

	@Override
	protected void applyTransformation(final float interpolatedTime, Transformation t) {
		final float fromDegrees = mFromDegrees;
		float degrees = fromDegrees
				+ ((mToDegrees - fromDegrees) * interpolatedTime);

//		float scale = scaleX*
		
		final float centerX = mCenterX;
		final float centerY = mCenterY;
		final Camera camera = mCamera;

		final Matrix matrix = t.getMatrix();

		camera.save();

		camera.rotateY(degrees);

		camera.getMatrix(matrix);
		camera.restore();

//		if(interpolatedTime == 1){
//			matrix.preScale(2*scaleX, 2*scaleY);
//		}else
//			matrix.preScale(scaleX, scaleY);

		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);
	}
}