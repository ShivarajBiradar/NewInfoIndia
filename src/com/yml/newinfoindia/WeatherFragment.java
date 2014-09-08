package com.yml.newinfoindia;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class WeatherFragment extends Fragment{
	Button value;
	Spinner operator1 ;
	Spinner circle;
	ListView listview;
	public WeatherFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View rootView = inflater.inflate(R.layout.weather_list, container, false);
		operator1=(Spinner)rootView.findViewById(R.id.operatorSpinner);
		circle = (Spinner)rootView.findViewById(R.id.circleSpinner);
		listview=(ListView)rootView.findViewById(R.id.listView1);
		
		value = (Button)rootView.findViewById(R.id.submit);
	final	Context mContext;
		mContext=rootView.getContext();
		
		value.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String operator = operator1.getSelectedItem().toString();
				String location = circle.getSelectedItem().toString();
				Log.d("Telcome", "Telcomedata");
				Log.d("Spinner Value","item0"+operator);
				String weatherApi="http://api.dataweave.in/v1/indian_weather/findByCity/?api_key=b20a79e582ee4953ceccf41ac28aa08d&city="+operator+"&date="+location+"";
				
				dislpayList(weatherApi);
				
			}
			private void dislpayList(String telecomApi) {
				
				String value = "Telcom";
				
				new WeatherParseClass(mContext, listview).execute(telecomApi);
				}
		});
		 return rootView;
	
	}

}
