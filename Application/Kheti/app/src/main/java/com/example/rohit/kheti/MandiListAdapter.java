package com.example.rohit.kheti;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rohit on 3/18/2018.
 */

public class MandiListAdapter extends RecyclerView.Adapter<MandiListAdapter.MyViewHolder> {

    private List<MandiListItem> mlist;
    private Context context;
    private Activity activity;
    private ArrayList<MandiListItem> myyList; // = new ArrayList<>();
    private RecyclerView recyclerView;
    private MandiListAdapter mAdapter;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView iname, avgprice,lowprice, highprice;
        public ImageView itemimg;
        public MyViewHolder(View view) {
            super(view);
            iname = (TextView) view.findViewById(R.id.mandicardtexttop);
            avgprice = (TextView) view.findViewById(R.id.mandicardtt1);
            lowprice = (TextView) view.findViewById(R.id.mandicardtt2);
            highprice = (TextView) view.findViewById(R.id.mandicardtt3);
            itemimg = (ImageView) view.findViewById(R.id.mandicardimg);
        }
    }


    public void notifyData(ArrayList<MandiListItem> myList) {
        Log.v("notifyData ", myList.size() + "");
        this.mlist = myList;
        notifyDataSetChanged();
    }

    public MandiListAdapter(Context c,Activity activity,List<MandiListItem> mlist) {
        this.context=c;
        this.activity=activity;
        this.mlist = mlist;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mandicard, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MandiListAdapter.MyViewHolder holder, int position) {
        MandiListItem mylist  = mlist.get(position);

        holder.lowprice.setText(String.valueOf(mylist.getPricelow()));
        holder.avgprice.setText(String.valueOf(mylist.getPricemiddle()));
        holder.highprice.setText(String.valueOf(mylist.getPricehigh()));
        Log.v("imgdata","i"+String.valueOf(mylist.getDist())+"_"+String.valueOf(mylist.getId()));
        int id = context.getResources().getIdentifier("i"+String.valueOf(mylist.getDist())+"_"+String.valueOf(mylist.getId()), "drawable", context.getPackageName());
        holder.itemimg.setImageResource(id);
        id = context.getResources().getIdentifier("i"+String.valueOf(mylist.getDist())+"_"+String.valueOf(mylist.getId()), "string", context.getPackageName());
        String strTest = context.getResources().getString(id);
        holder.iname.setText(strTest);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
