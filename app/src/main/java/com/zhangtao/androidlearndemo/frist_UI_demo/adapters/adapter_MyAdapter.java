package com.zhangtao.androidlearndemo.frist_UI_demo.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhangtao.androidlearndemo.R;

public class adapter_MyAdapter extends RecyclerView.Adapter {
   private Context mContext;
   private  PopupWindow mpw;
    String[][] mdata;

    class view extends RecyclerView.ViewHolder{
        private TextView tv;
        private Button btn;
        public view(@NonNull View view) {
            super(view);
            tv = view.findViewById(R.id.tv_text);
            btn = view.findViewById(R.id.button);
        }
        public TextView getTv(){
            return tv;
        }
        public Button getBtn(){return btn;}
    }
    public adapter_MyAdapter(Context context, PopupWindow pw, String[][] data){
        mContext = context;
        mpw = pw;
        mdata = data;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new view(new TextView(parent.getContext()));
        return new view(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        view tv = (view)holder;
        tv.getTv().setText(mdata[position][0]);
        tv.getBtn().setText(mdata[position][1]);
        tv.getBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "点击该控件"+position, Toast.LENGTH_SHORT).show();
                if (mpw != null && mpw.isShowing()) {
                    mpw.dismiss();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdata.length;
    }
}
