package com.yml.newinfoindia;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

public class WeatherParseClass  extends AsyncTask<String, String, ArrayList<WeatherClass>>{
	
	Context context;
	InputStream is;
	ArrayList<WeatherClass> cl;
	ListView lv;
	private ProgressDialog pDialog;
	
	public WeatherParseClass( Context context,ListView cl) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.lv=cl;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pDialog=new ProgressDialog(context);
		pDialog.setMessage("Please Wait");
		pDialog.setCancelable(false);
		pDialog.show();
	}
	
	@Override
	protected ArrayList<WeatherClass> doInBackground(String... finalURL) {
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
			System.out.print(result);
			
			Log.d("DOing in Background", "FINISHED url");

			JSONObject readerObject = new JSONObject(result);

			JSONArray dataArray = readerObject.getJSONArray("data");
			
			JSONObject dataObject = dataArray.getJSONObject(0);
			//JSONArray minimumTemp = dataObject.getJSONArray("Minimum Temp");
			
			
			
			//String ss = dataArray.toString();
			//Log.d("weather maximum", ss);
			
			WeatherClass obj;
			cl = new ArrayList<WeatherClass>();
			JSONArray MinimumArray = dataObject.getJSONArray("Minimum Temp");
			
			String Minimum=MinimumArray.getString(0)+MinimumArray.getString(1);
			
			String date=dataObject.getString("date");
			
			
			
			//for (int i = 0; i < 10; i++) {

			//JSONObject a = dataArray.getJSONObject(i);

				Log.d("DOing in Background", "FINISHED url");

				

		
				//String value=recharge_value;
				
		
				
			//JSONArray date=dataObject.getString("date");

	

				JSONArray maximumArray=dataObject.getJSONArray("Maximum Temp");
				
				String maximum=maximumArray.getString(0)+maximumArray.getString(1);

		

				JSONArray moonsetArray=dataObject.getJSONArray("Moonset");
				String moonset=moonsetArray.getString(0)+moonsetArray.getString(1);

				JSONArray sunsetArray=dataObject.getJSONArray("Todays Sunset");
				String sunset=sunsetArray.getString(0)+sunsetArray.getString(1);
				obj = new WeatherClass(Minimum,date, maximum,moonset,sunset);

				cl.add(obj);
				
				System.out.println(cl.toString());
			//}
			Log.d("onEXECUTE","HI there" );
			return cl;
			
		} catch (JSONException e) {
			return null;
		}catch (Exception e) {
			return null;
		}
				
}
	
	
	
	@Override
	protected void onPostExecute(ArrayList<WeatherClass> result) {
		if(pDialog.isShowing())
		{
			pDialog.dismiss();
		}
		
		
		// TODO Auto-generated method stub
try {

			
			Log.d("onPostEXECUTE","seems to be done with parsing");
	
//			Log.d("onPostEXECUTE","HI" + result.toString());
			
			
		WeatherAdapterClass aa = new WeatherAdapterClass(context, result);

			lv.setAdapter(aa);

			is.close();
			
		
	}
catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


	
	}

}
