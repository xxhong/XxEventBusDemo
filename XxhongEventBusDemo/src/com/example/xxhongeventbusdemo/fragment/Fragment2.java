package com.example.xxhongeventbusdemo.fragment;

import com.example.xxhongeventbusdemo.R;
import com.example.xxhongeventbusdemo.eventtype.EventType;

import de.greenrobot.event.EventBus;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment2 extends Fragment {
	private TextView textView;
	public void onEventMainThread(EventType et){
		Log.i("xxhong", "Fragment2");
		String data = et.getData();
		textView.setText(data);
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		EventBus.getDefault().register(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_two, null);
		textView = (TextView) view.findViewById(R.id.textView);
		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

}
