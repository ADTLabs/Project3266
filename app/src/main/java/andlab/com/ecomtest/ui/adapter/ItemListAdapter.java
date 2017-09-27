package andlab.com.ecomtest.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import andlab.com.ecomtest.R;

/**
 * Created by Ravi Prakash on 23-07-2017.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.MyViewHolder> {

    private List<String> horizontalList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtView;
        public ImageView imgView;
        PostItemListener mItemListener;
        public MyViewHolder(View view, PostItemListener postItemListener) {
            super(view);
            txtView = (TextView) view.findViewById(R.id.itemname);
            imgView = (ImageView) view.findViewById(R.id.itemicon);
            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            String item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item);

            notifyDataSetChanged();
        }
    }

    private PostItemListener mItemListener;

    public ItemListAdapter(List<String> horizontalList,PostItemListener itemListener) {
        this.horizontalList = horizontalList;
        mItemListener = itemListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        return new MyViewHolder(itemView,mItemListener);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.txtView.setText(horizontalList.get(position));
    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }

    public interface PostItemListener {
        void onPostClick(String id);
    }

    private String getItem(int adapterPosition) {
        return horizontalList.get(adapterPosition);
    }
}
