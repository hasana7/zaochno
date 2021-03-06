package ru.zaochno.zaochno.trainings.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.zaochno.zaochno.R;
import ru.zaochno.zaochno.model.Training;
import ru.zaochno.zaochno.trainings.DefaultTrainingsFragment;

/**
 * Created by notbl on 5/21/2017.
 */

public class TrainingsListViewAdapter extends BaseAdapter{


    private List<Training> data;
    private LayoutInflater mInflater;
    private DefaultTrainingsFragment mDefaultTreningsFragment;

    public TrainingsListViewAdapter(Fragment fragment, List<Training> data){
        this.data=data;
        mInflater = (LayoutInflater) fragment.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDefaultTreningsFragment=(DefaultTrainingsFragment)fragment;
    }
    public TrainingsListViewAdapter(Fragment fragment){
        this(fragment,new ArrayList<Training>());
    }

    public void setData(List<Training> data){
        this.data=data;
        notifyDataSetChanged();
    }
    public void setData(Training[] data){
        this.data=Arrays.asList(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.default_list_view_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.mTite=(TextView)convertView.findViewById(R.id.detailsListViewItemTitleId);
            viewHolder.mShortDescription=(TextView)convertView.findViewById(R.id.detailsListViewItemShosrtDescriptionId);
            viewHolder.mImageVew=(ImageView)convertView.findViewById(R.id.detailsListViewItemImageViewId);
            viewHolder.mDemoButton=(LinearLayout)convertView.findViewById(R.id.detailsListViewItemDemoButtonId);
            viewHolder.mBuyButton=(LinearLayout)convertView.findViewById(R.id.detailsListViewItemBuyButtonId);
            viewHolder.mFavoriteButton=(LinearLayout)convertView.findViewById(R.id.detailsListViewItemFavoritesButtonId);
            viewHolder.mListener=new TrainingItemClickListener(position);
            //set click listener
            convertView.setOnClickListener(viewHolder.mListener);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }

        viewHolder.mTite.setText(data.get(position).getTitle());
        viewHolder.mShortDescription.setText(data.get(position).getDescription());
        viewHolder.mDemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fakeId=0;
                mDefaultTreningsFragment.demoButtonClicked(fakeId);
            }
        });

        viewHolder.mBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDefaultTreningsFragment.buyTraining(data.get(position));
            }
        });

        viewHolder.mListener.setPosition(position);



        return convertView;
    }

    class TrainingItemClickListener implements View.OnClickListener{

        private int position;

        public TrainingItemClickListener(int position) {
            this.position = position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            mDefaultTreningsFragment.onTrainingItemClicked(position);
        }
    }


    static class ViewHolder {
        private TextView mShortDescription;
        private TextView mTite;
        private LinearLayout mDemoButton;
        private LinearLayout mFavoriteButton;
        private LinearLayout mBuyButton;
        private ImageView mImageVew;
        private TrainingItemClickListener mListener;
    }
}
