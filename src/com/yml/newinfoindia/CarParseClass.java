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

public class CarParseClass  extends AsyncTask<String, String, ArrayList<CarClass>>{
	Context context;
	//TextView ShortDecprtion;
	InputStream is;
	ArrayList<CarClass> cl;
	ListView lv;
	TextView tv;
	private ProgressDialog pDialog;
	//String recharge_value;
	public CarParseClass(Context cotext,ListView lv) {
		// TODO Auto-generated constructor stub
		this.context=cotext;
		this.lv=lv;
	
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
	protected ArrayList<CarClass> doInBackground(String... finalURL) {
		// TODO Auto-generated method stub
	//	return null;
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
			CarClass obj;
			cl = new ArrayList<CarClass>();
		
			for (int i = 0; i < 10; i++) {

				JSONObject a = dataArray.getJSONObject(i);

				Log.d("DOing in Background", "FINISHED url");
				String model=a.getString("model");
				String fuel_type=a.getString("fuel_type");
				String price=a.getString("price");
				String state=a.getString("state");
				String crawl_date=a.getString("crawl_date");
				
				
				obj=new CarClass(model, fuel_type, price, state, crawl_date);
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
	protected void onPostExecute(ArrayList<CarClass> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if(pDialog.isShowing())
		{
			pDialog.dismiss();
		}
		
try {

			
			Log.d("onPostEXECUTE","seems to be done with parsing");
	
			Log.d("onPostEXECUTE","HI" + result.toString());
			
			
			CarAdapterClass aa = new CarAdapterClass(context, result);

			lv.setAdapter(aa);

			is.close();
			
		
	}
catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


	}
	}
				


		
		
	
	
	

