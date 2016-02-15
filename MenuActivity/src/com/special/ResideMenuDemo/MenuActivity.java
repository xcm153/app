package com.special.ResideMenuDemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MenuActivity extends FragmentActivity implements
		View.OnClickListener {
	
	private ResideMenu resideMenu;
	private MenuActivity mContext;
	private ResideMenuItem itemHome;
	private ResideMenuItem itemProfile;
	private ResideMenuItem itemCalendar;
	private ResideMenuItem itemSettings;
	
	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.special.ResideMenuDemo.R.layout.main);
		 
		 
		
		mContext = this;
		setUpMenu();
		if (savedInstanceState == null)
			changeFragment(new HomeFragment());
	}
	 

	
	 
	 

	

	private void setUpMenu() {

		// attach to current activity;
		resideMenu = new ResideMenu(this);
		resideMenu.setUse3D(true);
		resideMenu.setBackground(com.special.ResideMenuDemo.R.drawable.menu_background);
		resideMenu.attachToActivity(this);

		// valid scale factor is between 0.0f and 1.0f. leftmenu'width is
		// 150dip.
		resideMenu.setScaleValue(0.6f);

		// create menu items;
		itemHome = new ResideMenuItem(this, com.special.ResideMenuDemo.R.drawable.icon_home, "搜索");
		itemProfile = new ResideMenuItem(this, com.special.ResideMenuDemo.R.drawable.icon_profile, "关于");
		itemCalendar = new ResideMenuItem(this,com.special.ResideMenuDemo. R.drawable.icon_calendar, "记录");
		itemSettings = new ResideMenuItem(this, com.special.ResideMenuDemo.R.drawable.icon_settings, "设置");

		itemHome.setOnClickListener(this);
		itemProfile.setOnClickListener(this);
		itemCalendar.setOnClickListener(this);
		itemSettings.setOnClickListener(this);

		resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_RIGHT);
		resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_RIGHT);

		// You can disable a direction by setting ->
		// resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}

	@Override
	public void onClick(View view) {

		if (view == itemHome) {
			changeFragment(new HomeFragment());
		} else if (view == itemProfile) {
			changeFragment(new ProfileFragment());
		} else if (view == itemCalendar) {
			changeFragment(new CalendarFragment());
		} else if (view == itemSettings) {
			changeFragment(new SettingsFragment());
		}

		resideMenu.closeMenu();
	}

	private void changeFragment(Fragment targetFragment) {
		resideMenu.clearIgnoredViewList();
		getSupportFragmentManager().beginTransaction()
				.replace(com.special.ResideMenuDemo.R.id.main_fragment, targetFragment, "fragment")
				.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.commit();
	}

	// What good method is to access resideMenu？
	public ResideMenu getResideMenu() {
		return resideMenu;
	}
	 
	
}
