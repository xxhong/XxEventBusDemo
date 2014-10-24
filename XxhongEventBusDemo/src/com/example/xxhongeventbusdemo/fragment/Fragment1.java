package com.example.xxhongeventbusdemo.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.xxhongeventbusdemo.R;
import com.example.xxhongeventbusdemo.eventtype.EventType;

import de.greenrobot.event.EventBus;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Fragment1 extends Fragment {
	public void onEventMainThread(EventType type){
		Log.i("xxhong", "Fragment1 onEventMainThread is main thread<<"+(Looper.myLooper() == Looper.getMainLooper()));
	}
	public void onEventBackgroundThread(String type){
      Log.d("xxhong",
      "Received ItemListEvent, is main thread:" + (Looper.myLooper() == Looper.getMainLooper()));
		Log.i("xxhong", "Fragment1 onEventBackgroundThread<<"+type+" is main thread<<"+(Looper.myLooper() == Looper.getMainLooper()));
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_one, null);
		ListView lv = (ListView) view.findViewById(R.id.listView1);
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		List<String> lists = new ArrayList<String>();
		lists.add("item 1");
		lists.add("item 2");
		lists.add("item 3");
		lists.add("item 4");
		lists.add("item 5");
		lists.add("item 6");
		lists.add("item 7");
		lists.add("item 8");
		lists.add("item 9");
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, lists);
		lv.setAdapter(arrayAdapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				EventBus.getDefault().post(new EventType(position+""));
				EventBus.getDefault().post("sb");
			}
		});
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
