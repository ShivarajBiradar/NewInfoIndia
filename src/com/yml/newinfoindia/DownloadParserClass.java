package com.yml.newinfoindia;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;



import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class DownloadParserClass extends AsyncTask<String, String, ArrayList<CommidityClass>>{
	
	Context context;
	TextView ShortDecprtion;
	InputStream is;
	ArrayList<CommidityClass> cl;
	ListView lv;
	TextView tv;
	String recharge_value;
	private ProgressDialog pDialog;
	
	public DownloadParserClass( Context cotext,ListView lv) {
		// TODO Auto-generated constructor stub
		this.context=cotext;
		this.lv=lv;
		//.this.recharge_value=recharge_value;
		
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pDialog = new ProgressDialog(context);
		pDialog.setMessage("Please wait...");
		pDialog.setCancelable(false);
		pDialog.show();

		
	}
	
	@Override
	protected ArrayList<CommidityClass> doInBackground(String... finalURL) {
		// TODO Auto-generated method stub
		try {

			String link = finalURL[0];
			URL url = new URL(link);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);

			conn.connect();

			is = conn.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));

			String data = null;

			String result = "";

			while ((data = reader.readLine()) != null) {
				result += data + "\n";
			}
			
			Log.d("DOing in Background", "FINISHED url");

			JSONObject readerObject = new JSONObject(result);

			JSONArray dataArray = readerObject.getJSONArray("data");

			String ss = dataArray.toString();
			Log.d("recharge_value", ss);

			CommidityClass obj;
			cl = new ArrayList<CommidityClass>();
		
			for (int i = 0; i < 10; i++) {

				JSONObject a = dataArray.getJSONObject(i);

				Log.d("DOing in Background", "FINISHED url");

				

				String recharge_value1 = a.getString("recharge_value");
				//String value=recharge_value;
				
		
				
				String recharge_Talktime=a.getString("recharge_talktime");

	

				String recharge_Validity=a.getString("recharge_validity");

		

				String recharge_short_description=a.getString("recharge_short_description");


				String recharge_description=a.getString("recharge_description");


	
			//	String circle_master=a.getString("circle_master");
			//	String operator_master=a.getString("operator_master");
				
				obj = new CommidityClass(recharge_value1,recharge_Talktime, recharge_Validity,recharge_short_description,recharge_description);

				cl.add(obj);
				
				System.out.println(cl.toString());
				
				
			}
			Log.d("onEXECUTE","HI there" );
			return cl;
			
		} catch (Exception e) {
			return null;
		}

	}
	
	@Override
	protected void onPostExecute(ArrayList<CommidityClass> result) {
		// TODO Auto-generated method stub
		if(pDialog.isShowing())
		{
			pDialog.dismiss();
		}
		
		
try {

			
			Log.d("onPostEXECUTE","seems to be done with parsing");
	
			Log.d("onPostEXECUTE","HI" + result.toString());
			
			
			SearchAdopterClass aa = new SearchAdopterClass(context, result);

			lv.setAdapter(aa);

			is.close();
			
		
	}
catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


	}
}
	

	
	
	
	
	


