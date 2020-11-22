package com.residencias.ficosec.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.residencias.ficosec.Interface.ItemClickListener;
import com.residencias.ficosec.R;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtProductName, txtProductDescription,txtProductStatus, txtProductPrice;
    public ImageView imageView;
    public ItemClickListener listener;
    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.product_seller_image);
        txtProductName = itemView.findViewById(R.id.product_seller_name);
        txtProductDescription = itemView.findViewById(R.id.product_seller_description);
        txtProductPrice = itemView.findViewById(R.id.product_seller_price);
        txtProductStatus = itemView.findViewById(R.id.product_state);
    }
    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(),false);
    }
}
