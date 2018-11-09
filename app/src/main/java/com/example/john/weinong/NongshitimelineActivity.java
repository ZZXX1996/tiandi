package com.example.john.weinong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.john.weinong.dbService.Land;
import com.example.john.weinong.dbService.Work;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by john on 2018/10/15.
 */

public class NongshitimelineActivity extends AppCompatActivity {
    private List<Work> workList=new ArrayList<>();
    private OnenongshiAdapter adapter;
    private RecyclerView recyclerView;
    private String ObjectId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nongshiactivity);
        recyclerView=(RecyclerView)findViewById(R.id.nongshiactivityrecycleview);

    }
    private void getData()
    {  workList.clear();
        Intent intent=getIntent();
        ObjectId=intent.getStringExtra("landobjectid");
       BmobQuery<Work> query = new BmobQuery<Work>();
        Land land=new Land();
        land.setObjectId(ObjectId);
        query.addWhereEqualTo("landid",land);
        query.order("-updatedAt");
        query.findObjects(new FindListener<Work>() {

            @Override
            public void done(List<Work> object,BmobException e) {
                if(e==null){
                    for(int i=0;i<object.size();i++)
                    {
                        workList.add(object.get(i));
                    }

                    adapter = new OnenongshiAdapter(NongshitimelineActivity.this, workList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(NongshitimelineActivity.this));
                    recyclerView.setAdapter(adapter);
                }else{
                    Toast.makeText(NongshitimelineActivity.this,"服务器连接失败",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}
