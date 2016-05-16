package chenyu.com.cardviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/5/12.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<MessageObj> mData;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview,viewGroup,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    public MyAdapter(List<MessageObj> data){
        mData = data;
    }
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.mUsername.setText(mData.get(i).getUsername());
        myViewHolder.mUserIcon.setImageResource(mData.get(i).getIcon());
        myViewHolder.mCount.setText(mData.get(i).getCount());
        myViewHolder.mTitle.setText(mData.get(i).getTitle());
        myViewHolder.mContent.setText(mData.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mUsername;
        public TextView mCount;
        public TextView mTitle;
        public TextView mContent;
        public ImageView mUserIcon;
        public MyViewHolder(View itemView) {
            super(itemView);
            mUsername = (TextView) itemView.findViewById(R.id.username);
            mUserIcon = (ImageView) itemView.findViewById(R.id.usericon);
            mCount = (TextView) itemView.findViewById(R.id.count);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mContent = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
