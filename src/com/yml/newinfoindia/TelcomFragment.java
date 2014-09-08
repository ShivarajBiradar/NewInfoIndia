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

public class TelcomFragment  extends Fragment{
	
	Button value;
	Spinner operator1 ;
	Spinner circle;
	ListView listview;
	public TelcomFragment() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.activity_spinner_, container, false);
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
				String telecomApi="http://api.dataweave.in/v1/telecom_data/listByCircle/?api_key=76fa55b10b3ca8ea99bcbcd26091c209cd43ee82&operator="+operator+"&circle="+location+"&page=1&per_page=10";
				
				
				dislpayList(telecomApi);
				
			}
			private void dislpayList(String telecomApi) {
			
				String value = "Telcom";
				
				new DownloadParserClass(mContext, listview).execute(telecomApi);
				}
		});
				
		 return rootView;
		
		
	}

	
}
