package com.kbstar.m01weblist;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.DataViewHolder>
{
    private ArrayList<UserData> userList = null;
    private Activity context = null;

    public UserAdapter(Activity context, ArrayList<UserData> list)
    {
        this.context = context;
        this.userList = list;
    }

    class DataViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tv_idx;
        private TextView tv_name;
        private TextView tv_id;
        private TextView tv_level;
        private TextView tv_memo;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_idx = itemView.findViewById(R.id.tv_idx);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_level = itemView.findViewById(R.id.tv_level);
            this.tv_memo = itemView.findViewById(R.id.tv_memo);
        }
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_list,
                parent,
                false);

        DataViewHolder viewHolder = new DataViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int pos) {
        holder.tv_idx.setText( userList.get(pos).getUserIdx());
        holder.tv_name.setText( userList.get(pos).getUserName());
        holder.tv_id.setText( userList.get(pos).getUserId());
        holder.tv_memo.setText(userList.get(pos).getUserMemo());

        String printLevel = "";
        if(userList.get(pos).getUserLevel().equals("1"))
            printLevel = "회원";
        else if(userList.get(pos).getUserLevel().equals("2"))
            printLevel = "관리자";
        else
            printLevel = "기타";

        //holder.tv_level.setText( userList.get(pos).getUserLevel());
        holder.tv_level.setText( printLevel );

        holder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("XXXXX", "click idx = " + holder.tv_idx.getText().toString());

                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("idx", holder.tv_idx.getText().toString());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
