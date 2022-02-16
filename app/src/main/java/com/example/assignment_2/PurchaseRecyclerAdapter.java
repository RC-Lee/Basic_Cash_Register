package com.example.assignment_2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PurchaseRecyclerAdapter extends RecyclerView.Adapter<PurchaseRecyclerAdapter.PurchaseViewHolder> {
    ArrayList<Purchase> purchaseList;
    Context context;

    public PurchaseRecyclerAdapter(ArrayList<Purchase> purchaseList, Context context) {
        this.purchaseList = purchaseList;
        this.context = context;
    }

    @NonNull
    @Override
    public PurchaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_row_layout, parent, false);
        return new PurchaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseViewHolder holder, int position) {
        String productName = purchaseList.get(position).productType;
        String quantity = String.valueOf(purchaseList.get(position).quantity);
        String total = purchaseList.get(position).price;

        Purchase selected = purchaseList.get(position);

        holder.productText.setText(productName);
        holder.quantityText.setText(quantity);
        holder.priceText.setText(total);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context viewContext = view.getContext();
                Intent intent = new Intent(viewContext, Activity_Detail.class);
                intent.putExtra("selected", selected);
                viewContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return purchaseList.size();
    }

    public static class PurchaseViewHolder extends RecyclerView.ViewHolder{
        TextView productText;
        TextView quantityText;
        TextView priceText;

        public PurchaseViewHolder(@NonNull View itemView) {
            super(itemView);
            productText = itemView.findViewById(R.id.purchase_item_name);
            quantityText = itemView.findViewById(R.id.purchase_quantity);
            priceText = itemView.findViewById(R.id.purchase_total);

            // itemView.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View view) {
            //        Context viewContext = view.getContext();
            //        Intent intent = new Intent(viewContext, Activity_Purchase_Detail.class);
            //        viewContext.startActivity(intent);
            //    }
            //});
        }
    }
}
