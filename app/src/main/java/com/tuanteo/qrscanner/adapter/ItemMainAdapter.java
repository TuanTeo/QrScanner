package com.tuanteo.qrscanner.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tuanteo.qrscanner.R;
import com.tuanteo.qrscanner.listener.ItemAdapterListener;
import com.tuanteo.qrscanner.object.ItemCardView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ItemMainAdapter extends RecyclerView.Adapter<ItemMainAdapter.ViewHolder> {
    private ItemAdapterListener mListener;
    private ArrayList<ItemCardView> mListCardView;
    private Context mContext;

    public ItemMainAdapter(Context context, ArrayList<ItemCardView> listCardView, ItemAdapterListener listen) {
        mListener = listen;
        mListCardView = listCardView;
        mContext = context;
    }

    @Override
    public ItemMainAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_item_card_view, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.getView().setBackgroundColor(mListCardView.get(position).getBackground());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.getItemLogoImageView().setImageDrawable(
                    mContext.getDrawable(mListCardView.get(position).getLogo()));
        }
        holder.getItemNameTextView().setText(mListCardView.get(position).getNameItem());
    }

    @Override
    public int getItemCount() {
        return mListCardView.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView itemLogoImageView;
        private TextView itemNameTextView;
        private View mView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            mView = itemView;
            itemLogoImageView = itemView.findViewById(R.id.item_logo);
            itemNameTextView = itemView.findViewById(R.id.item_name);
            itemView.setOnClickListener(this);
        }

        public ImageView getItemLogoImageView() {
            return itemLogoImageView;
        }

        public TextView getItemNameTextView() {
            return itemNameTextView;
        }

        public View getView() {
            return mView;
        }

        @Override
        public void onClick(View v) {
            mListener.showWebView(getAdapterPosition());
        }
    }
}
