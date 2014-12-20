package com.digitalforce.datingapp.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.digitalforce.datingapp.R;
import com.digitalforce.datingapp.model.NearBy;
import com.digitalforce.datingapp.model.UserInfo;
import com.digitalforce.datingapp.utils.PicassoEx;
import com.digitalforce.datingapp.widgets.RoundedImageView;
import com.farru.android.utill.StringUtils;

public class NearByAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<UserInfo> mlistNearby;
	private LayoutInflater inflater;

	public NearByAdapter(Context context, ArrayList<UserInfo> mlistNearby)
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
	public UserInfo getItem(int position) {
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
		UserInfo nearBy = getItem(position);
		if(convertView==null){
			viewHolder = new ViewHolder();

			convertView = inflater.inflate(R.layout.layout_grid_nearby_details, parent, false);

			viewHolder.infoDistance = (LinearLayout)convertView.findViewById(R.id.info_distance);
			viewHolder.infoLayout = (LinearLayout)convertView.findViewById(R.id.info_layout);
			viewHolder.member = (TextView) convertView.findViewById(R.id.txt_nearby_member_name);
			viewHolder.place = (TextView) convertView.findViewById(R.id.txt_nearby_place);
			viewHolder.image = (RoundedImageView) convertView.findViewById(R.id.img_nearby_member);

            viewHolder.country = (TextView) convertView.findViewById(R.id.txt_country);

			convertView.setTag(viewHolder);

		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}


		if(!StringUtils.isNullOrEmpty(nearBy.getFirstName())){
			
			viewHolder.infoLayout.setVisibility(View.VISIBLE);
			viewHolder.member.setText(nearBy.getFirstName());
			
		}else if(!StringUtils.isNullOrEmpty(nearBy.getLastName())){
			
			viewHolder.infoLayout.setVisibility(View.VISIBLE);
			viewHolder.member.setText(nearBy.getLastName());

		}else{
			viewHolder.infoLayout.setVisibility(View.INVISIBLE);
			
		}

        if(!StringUtils.isNullOrEmpty(nearBy.getCountry())){

            viewHolder.country.setText(nearBy.getCountry());

        }else{
            viewHolder.country.setVisibility(View.INVISIBLE);
        }

		if(!StringUtils.isNullOrEmpty(nearBy.getDistance())){
			viewHolder.infoDistance.setVisibility(View.VISIBLE);
			viewHolder.place.setText(nearBy.getDistance());
		}else{
			viewHolder.infoDistance.setVisibility(View.INVISIBLE);
		}




		if(!StringUtils.isNullOrEmpty(nearBy.getImage())){
			picassoLoad(nearBy.getImage(), viewHolder.image);
		}
		return convertView;
	}

	public class ViewHolder{
		public TextView member;
		public TextView place;
        public TextView country;
		public RoundedImageView image;
		public LinearLayout infoLayout;
		public LinearLayout infoDistance;
	}

	public void picassoLoad(String url, ImageView imageView) {
		PicassoEx.getPicasso(context).load(url).error(R.drawable.farhan).placeholder(R.drawable.farhan).fit().into(imageView);
	}



}
