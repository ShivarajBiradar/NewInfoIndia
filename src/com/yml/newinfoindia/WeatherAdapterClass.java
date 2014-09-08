package com.yml.newinfoindia;

import java.util.List;

import com.yml.newinfoindia.SearchAdopterClass.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WeatherAdapterClass  extends BaseAdapter{

	Context mContext;
	List<WeatherClass> mList;
	
	
	public WeatherAdapterClass(Context contex, List<WeatherClass> al) {
		// TODO Auto-generated constructor stub
		this.mContext = contex;
		this.mList = al;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
		}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return mList.indexOf(getItem(position));
	}
	
	static class ViewHolder {
		TextView minimum, date, maximum, moonset, sunset;

	}

	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder holder = null;
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			
		

				convertView = inflater.inflate(R.layout.weatherlist,
						parent, false);
				holder = new ViewHolder();
				holder.minimum=(TextView)convertView.findViewById(R.id.value);
				holder.date=(TextView)convertView.findViewById(R.id.talktime);
				holder.maximum=(TextView)convertView.findViewById(R.id.validity);
				holder.moonset=(TextView)convertView.findViewById(R.id.sDecrption);
				holder.sunset=(TextView)convertView.findViewById(R.id.Decription);
				//holder.circle_master=(TextView)convertView.findViewById(R.id.copertor);
				//holder.operator_master=(TextView)convertView.findViewById(R.id.Mopretor);
				convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		WeatherClass Item = (WeatherClass) getItem(position);
		
		holder.minimum.setText(Item.minimum);
		holder.date.setText(Item.date);
		holder.maximum.setText(Item.maximum);
		holder.moonset.setText(Item.moonset);
		holder.sunset.setText(Item.sunset);
		//holder.circle_master.setText(Item.circle_master);
		//holder.operator_master.setText(Item.operator_master);

		return convertView;
	
	}
}
