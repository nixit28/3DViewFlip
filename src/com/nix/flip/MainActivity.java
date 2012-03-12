package com.nix.flip;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView text1;
	private TextView text2;

	private boolean isFirstImage = true;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		text1 = (TextView) findViewById(R.id.textView02);
		text2 = (TextView) findViewById(R.id.textView01);
		text2.setVisibility(View.GONE);

		text1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (isFirstImage) {
					applyRotation(0, 90);
					isFirstImage = !isFirstImage;

				} else {
					applyRotation(0, -90);
					isFirstImage = !isFirstImage;
				}
			}
		});
		
		text2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (isFirstImage) {
					applyRotation(0, 90);
					isFirstImage = !isFirstImage;

				} else {
					applyRotation(0, -90);
					isFirstImage = !isFirstImage;
				}
			}
		});
	}

	private void applyRotation(float start, float end) {
		// Find the center of image
		final float centerX = text1.getWidth() / 2.0f;
		final float centerY = text1.getHeight() / 2.0f;

		// AnimationSet is used to have multiple animation together
		AnimationSet animSet = new AnimationSet(false);
		
		//Use this constructor for zoom in and out @ center of the view
//		Scale3DAnimation zoom = new Scale3DAnimation(1.0f, 0.7f, 1.0f, 0.7f, centerX, centerY);
		
		//Use this constructor for zoom in and out for normal view
		Scale3DAnimation zoom = new Scale3DAnimation(1.0f, 0.7f, 1.0f, 0.7f);
		
		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final Flip3dAnimation rotation = new Flip3dAnimation(start, end,
				centerX, centerY);
		
		rotation.setDuration(600);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
//		rotation.setAnimationListener(new DisplayNextView(isFirstImage, text1, text2));
		
		zoom.setDuration(600);
		zoom.setInterpolator(new AccelerateInterpolator());
		zoom.setFillAfter(true);
		
		animSet.addAnimation(rotation);
		animSet.addAnimation(zoom);
		
		// listener for next animation
		animSet.setAnimationListener(new DisplayNextView(isFirstImage, text1, text2));
		
		if (isFirstImage) {
			text1.startAnimation(animSet);
		} else {
			text2.startAnimation(animSet);
		}
	}
}