package com.example.john.weinong;

/**
 * Created by john on 2018/10/24.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.john.weinong.dbService.Work;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by john on 2018/10/15.
 */
public class OnenongshiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private List<Work> traceList = new ArrayList<>(1);
    private Context mContext;


    public OnenongshiAdapter(Context context, List<Work> traceList) {
        inflater = LayoutInflater.from(context);
        this.traceList = traceList;
        this.mContext=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.onelandnongshi, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        ViewHolder itemHolder = (ViewHolder) holder;
            itemHolder.tvDot.setBackgroundResource(R.drawable.timelline_dot_first);
        itemHolder.bindHolder(traceList.get(position));
        ((ViewHolder) holder).linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("workobjectid",traceList.get(position).getObjectId());
                intent.setClass(mContext,NngshixiangqingActivity.class);
                mContext.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return traceList.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder {
        private TextView operationTime, detailedactivity,work;
        private TextView tvTopLine, tvDot;
        private LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            operationTime = (TextView) itemView.findViewById(R.id.onenongshioperationTime);
            work = (TextView) itemView.findViewById(R.id.onenongshiwork);
            detailedactivity = (TextView) itemView.findViewById(R.id.onenongshidetailedactivity);
            tvDot = (TextView) itemView.findViewById(R.id.onenongshitvDot);
            tvTopLine = (TextView) itemView.findViewById(R.id.onenongshitvTopLine);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.onelandnongshiitem);

        }

        public void bindHolder(Work trace) {
            operationTime.setText(trace.getOperationtime());
            detailedactivity.setText(trace.getDetailedwork());
            work.setText(trace.getWork());

        }
    }
}

