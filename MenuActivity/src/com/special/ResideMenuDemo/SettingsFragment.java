package com.special.ResideMenuDemo;

import kll.dod.rtk.br.AdSize;
import kll.dod.rtk.br.AdView;
import kll.dod.rtk.st.SpotManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dd.morphingbutton.MorphingButton;
import com.dd.morphingbutton.impl.LinearProgressButton;
import com.dd.morphingbutton.utils.ProgressGenerator;

/**
 * User: special Date: 13-12-22 Time: 下午3:28 Mail: specialcyci@gmail.com
 */
public class SettingsFragment extends Fragment {
	private int mMorphCounter1 = 1;
	private View parentView;

	public int dimen(@DimenRes int resId) {
		return (int) getResources().getDimension(resId);
	}

	public int color(@ColorRes int resId) {
		return getResources().getColor(resId);
	}

	public int integer(@IntegerRes int resId) {
		return getResources().getInteger(resId);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(
				com.special.ResideMenuDemo.R.layout.settings, container, false);

		AdView adView = new AdView(getActivity(), AdSize.FIT_SCREEN);
		LinearLayout adLayout = (LinearLayout) parentView
				.findViewById(R.id.adLayout);
		adLayout.addView(adView);
		final LinearProgressButton btnMorph1 = (LinearProgressButton) parentView
				.findViewById(com.special.ResideMenuDemo.R.id.button1);
		btnMorph1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				SharedPreferences mySharedPreferences = getActivity()
						.getSharedPreferences("test", Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = mySharedPreferences.edit();
				editor.clear().commit();
				// Toast.makeText(this, "清除完毕",0).show();
				onMorphButton1Clicked(btnMorph1);
			}
		});
		morphToSquare(btnMorph1, 0);

		return parentView;
	}

	private void onMorphButton1Clicked(final LinearProgressButton btnMorph) {
		if (mMorphCounter1 == 0) {
			mMorphCounter1++;
			morphToSquare(btnMorph, integer(R.integer.mb_animation));
		} else if (mMorphCounter1 == 1) {
			mMorphCounter1 = 0;
			simulateProgress1(btnMorph);
		}
	}

	private void morphToSquare(final MorphingButton btnMorph, int duration) {
		MorphingButton.Params square = MorphingButton.Params.create()
				.duration(duration)
				.cornerRadius(dimen(R.dimen.mb_corner_radius_2))
				.width(dimen(R.dimen.mb_width_100))
				.height(dimen(R.dimen.mb_height_56))
				.color(color(R.color.mb_blue))
				.colorPressed(color(R.color.mb_blue_dark));
		btnMorph.morph(square);
	}

	private void morphToSuccess(final MorphingButton btnMorph) {
		MorphingButton.Params circle = MorphingButton.Params.create()
				.duration(integer(R.integer.mb_animation))
				.cornerRadius(dimen(R.dimen.mb_height_56))
				.width(dimen(R.dimen.mb_height_56))
				.height(dimen(R.dimen.mb_height_56))
				.color(color(R.color.mb_green))
				.colorPressed(color(R.color.mb_green_dark))
				.icon(R.drawable.ic_done);
		btnMorph.morph(circle);
	}

	private void simulateProgress1(@NonNull final LinearProgressButton button) {
		int progressColor = color(R.color.mb_purple);
		int color = color(R.color.mb_gray);
		int progressCornerRadius = dimen(R.dimen.mb_corner_radius_4);
		int width = dimen(R.dimen.mb_width_200);
		int height = dimen(R.dimen.mb_height_8);
		int duration = integer(R.integer.mb_animation);

		ProgressGenerator generator = new ProgressGenerator(
				new ProgressGenerator.OnCompleteListener() {
					@Override
					public void onComplete() {
						morphToSuccess(button);
						button.unblockTouch();
					}
				});
		button.blockTouch(); // prevent user from clicking while button is in
								// progress
		button.morphToProgress(color, progressColor, progressCornerRadius,
				width, height, duration);
		generator.start(button);
	}
}
