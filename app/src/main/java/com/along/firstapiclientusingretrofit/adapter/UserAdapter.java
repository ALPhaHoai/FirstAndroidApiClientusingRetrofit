package com.along.firstapiclientusingretrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.along.firstapiclientusingretrofit.R;
import com.along.firstapiclientusingretrofit.api.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private List<User> values;
    OnBottomReachedListener onBottomReachedListener;

    public UserAdapter(Context context, List<User> values) {
        this.context = context;
        this.values = values;
    }

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View bookView = inflater.inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(bookView);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.title.setText(values.get(position).getFirstName());

        holder.setItemClickListener(new ItemClickListener() {

            @Override
            public void onClick(View view, int position, boolean b) {
/*                //go to book detail
                Intent intent = new Intent(ListBookActivity.this, BookDetailActivity.class);
                intent.putExtra("bookId", books.get(position).getId());
                intent.putExtra("bookTitle", books.get(position).getTitle());
                startActivity(intent);*/
            }
        });

        if (values != null && values.size() > 5 && position >= values.size() - 5) {
//                Toast.makeText(getBaseContext(), "onBottomReachedListener", Toast.LENGTH_SHORT).show();
            onBottomReachedListener.onBottomReached(position);
        }

    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        ItemClickListener itemClickListener;
        public RelativeLayout parentLayout;
        public LinearLayout itemLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.user_name);
            parentLayout = (RelativeLayout) itemView.findViewById(R.id.activity_main);
            itemLayout = (LinearLayout) itemView.findViewById(R.id.user_item);
            itemView.setOnClickListener(this); // Mấu chốt ở đây , set sự kiên onClick cho View
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }
    }

}

interface OnBottomReachedListener {
    void onBottomReached(int position);
}

interface ItemClickListener {
    void onClick(View view, int position, boolean b);
}