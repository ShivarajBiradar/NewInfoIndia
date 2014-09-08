package com.yml.newinfoindia;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SearchAdopterClass extends BaseAdapter{
	Context mContext;
List<CommidityClass> mList;
public SearchAdopterClass(Context contex, List<CommidityClass> al) {

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
	TextView recharge_value,recharge_talktime,recharge_validity,operator_master,circle_master,recharge_short_description,recharge_description;

}


@Override
public View getView(int position, View convertView, ViewGroup parent) {
	// TODO Auto-generated method stub
	ViewHolder holder = null;
	LayoutInflater inflater = (LayoutInflater) mContext
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	if (convertView == null) {
		
	

			convertView = inflater.inflate(R.layout.list,
					parent, false);
			holder = new ViewHolder();
			holder.recharge_value=(TextView)convertView.findViewById(R.id.value);
			holder.recharge_talktime=(TextView)convertView.findViewById(R.id.talktime);
			holder.recharge_validity=(TextView)convertView.findViewById(R.id.validity);
			holder.recharge_short_description=(TextView)convertView.findViewById(R.id.sDecrption);
			holder.recharge_description=(TextView)convertView.findViewById(R.id.Decription);
			//holder.circle_master=(TextView)convertView.findViewById(R.id.copertor);
			//holder.operator_master=(TextView)convertView.findViewById(R.id.Mopretor);
			convertView.setTag(holder);
	}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		CommidityClass Item = (CommidityClass) getItem(position);
		
		holder.recharge_value.setText(Item.recharge_value);
		holder.recharge_talktime.setText(Item.recharge_talktime);
		holder.recharge_validity.setText(Item.recharge_validity);
		holder.recharge_short_description.setText(Item.recharge_short_description);
		holder.recharge_description.setText(Item.recharge_description);
		//holder.circle_master.setText(Item.circle_master);
		//holder.operator_master.setText(Item.operator_master);

		return convertView;
	
	
}
}




