package com.digitalforce.datingapp.adapter;

import java.util.ArrayList;

import com.digitalforce.datingapp.R;
import com.digitalforce.datingapp.model.NearBy;
import com.digitalforce.datingapp.utils.PicassoEx;
import com.digitalforce.datingapp.widgets.RoundedImageView;
import com.farru.android.utill.StringUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<NearBy> mlistNearby;
	private LayoutInflater inflater;
	public MatchAdapter(Context context, ArrayList<NearBy> mlistNearby)
	{
		this.context = context;
		this.mlistNearby = mlistNearby;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(mlistNearby != null && mlistNearby.size() > 0)
			return mlistNearby.size();
		return 0;
	}

	@Override
	public NearBy getItem(int position) {
		// TODO Auto-generated method stub
		if(mlistNearby != null && mlistNearby.size() > 0)
			return mlistNearby.get(position);
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder;
		NearBy nearBy = getItem(position);
		
		if(convertView==null)
		{
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.layout_grid_match_details, parent, false);
			
			viewHolder.image = (RoundedImageView) convertView.findViewById(R.id.img_match_member);
			viewHolder.member = (TextView) convertView.findViewById(R.id.txt_match_member_name);
			viewHolder.place = (TextView) convertView.findViewById(R.id.txt_match_place);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}
		
		if(!StringUtils.isNullOrEmpty(nearBy.getFirstName())){
			viewHolder.member.setVisibility(View.VISIBLE);
			viewHolder.member.setText(nearBy.getFirstName());

		}else if(!StringUtils.isNullOrEmpty(nearBy.getLastName())){
			viewHolder.member.setText(nearBy.getLastName());
			viewHolder.member.setVisibility(View.VISIBLE);
		}else if(!StringUtils.isNullOrEmpty(nearBy.getEmail())){
			viewHolder.member.setText(nearBy.getEmail());
			viewHolder.member.setVisibility(View.VISIBLE);
		}else{
			viewHolder.member.setText("Farhan");  // testing
			viewHolder.member.setVisibility(View.VISIBLE);
		}

		if(!StringUtils.isNullOrEmpty(nearBy.getDistance())){
			viewHolder.place.setVisibility(View.VISIBLE);
			viewHolder.place.setText(nearBy.getDistance());
		}else{
			viewHolder.place.setVisibility(View.GONE);
		}
		if(!StringUtils.isNullOrEmpty(nearBy.getImage())){
			picassoLoad(nearBy.getImage(), viewHolder.image);
		}
		
		return convertView;
		
		
	}

	public class ViewHolder
	{
		public TextView member;
		public TextView place;
		public RoundedImageView image;
	}
	
	public void picassoLoad(String url, ImageView imageView) {
        PicassoEx.getPicasso(context).load(url).error(R.drawable.farhan).placeholder(R.drawable.farhan).fit().into(imageView);
 }
}
