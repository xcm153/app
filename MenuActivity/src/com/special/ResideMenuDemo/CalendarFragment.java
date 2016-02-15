package com.special.ResideMenuDemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * User: special Date: 13-12-22 Time: 下午3:26 Mail: specialcyci@gmail.com
 */
public class CalendarFragment extends Fragment {
	private View parentView;

	private com.chiemy.pullseparate.PullSeparateListView aListView;
	private ArrayAdapter<String> adapter;
	private ArrayList<String> arrayList = new ArrayList<>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.calendar, container, false);

		aListView = (com.chiemy.pullseparate.PullSeparateListView) parentView
				.findViewById(R.id.pullExpandListView1);
		adapter = new ArrayAdapter<>(getActivity(), R.layout.item, R.id.text,
				arrayList);

		aListView.setAdapter(adapter);
		initView();
		return parentView;
	}

	private void initView() {
		SharedPreferences mySharedPreferences = getActivity()
				.getSharedPreferences("test", Activity.MODE_PRIVATE);

		for (int j = 0; j <= mySharedPreferences.getInt("size", 0); j++) {// System.out.println(mySharedPreferences.getInt("size",
																			// 0));
			if (mySharedPreferences.getString(j + "danci", null) == null)
				return;
			if (!arrayList.contains(mySharedPreferences.getString(j + "danci",
					""))) {
				arrayList.add(mySharedPreferences.getString(j + "danci", ""));
				arrayList.add(mySharedPreferences.getString(j + "ciyi", ""));
			}

		}
		adapter.notifyDataSetChanged();

	}
}
