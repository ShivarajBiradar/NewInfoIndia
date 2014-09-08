package com.yml.newinfoindia;

import java.util.List;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CarAdapterClass extends BaseAdapter{
	
	Context mContext;
	List<CarClass> mlist;	
	
	public CarAdapterClass(Context mcontext,List<CarClass> mlist) {
		
		// TODO Auto-generated constructor stub
		this.mContext=mcontext;
		this.mlist=mlist;
		
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mlist.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return mlist.indexOf(getItem(position));
	}
	
	
	static class ViewHolder {
		TextView model,fuel_type,price,state,crawl_date;

	}
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.carlist,
					parent, false);
			holder = new ViewHolder();
			holder.model=(TextView)convertView.findViewById(R.id.value);
			holder.fuel_type=(TextView)convertView.findViewById(R.id.talktime);
			holder.price=(TextView)convertView.findViewById(R.id.validity);
			holder.state=(TextView)convertView.findViewById(R.id.sDecrption);
			holder.crawl_date=(TextView)convertView.findViewById(R.id.Decription);
			convertView.setTag(holder);
		}
			else {
				holder = (ViewHolder) convertView.getTag();
			}
CarClass Item = (CarClass) getItem(position);
		
		holder.model.setText(Item.model);
		holder.fuel_type.setText(Item.fuel_type);
		holder.price.setText(Item.price);
		holder.state.setText(Item.state);
		holder.crawl_date.setText(Item.crawl_date);
		return convertView;
		}
			
	}


