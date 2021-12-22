package com.aleynakaracengel.a20173523.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aleynakaracengel.a20173523.R;
import com.aleynakaracengel.a20173523.database.BasketDao;
import com.aleynakaracengel.a20173523.database.DatabaseBasketCreator;
import com.aleynakaracengel.a20173523.model.OrderFood;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrderFoodAdapter extends RecyclerView.Adapter<OrderFoodAdapter.CardViewDesignThingsHolder>{
    private Context context;
    private ArrayList<OrderFood> orderFoodArrayList = new ArrayList<>();

    public OrderFoodAdapter(Context context, ArrayList<OrderFood> orderFoodArrayList) {
        this.context = context;
        this.orderFoodArrayList = orderFoodArrayList;
    }

    @NonNull
    @Override
    public CardViewDesignThingsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.orderfood, parent, false);
        return new CardViewDesignThingsHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CardViewDesignThingsHolder holder, int position) {
        OrderFood orderFood = orderFoodArrayList.get(position);

        holder.cardViewOrderFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Picasso.get()
                .load(Uri.parse(orderFood.getImageurl()))
                .into(holder.imageViewOrderFood);

        holder.textViewOrderFoodName.setText(orderFood.getName());
        holder.textViewOrderFoodPrice.setText("("+orderFood.getPrice()+" TL)");

        holder.imageViewOrderFoodAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseBasketCreator databaseBasketCreator = new DatabaseBasketCreator(context);
                new BasketDao().addBasket(databaseBasketCreator, orderFood.getImageurl(), orderFood.getName(), orderFood.getPrice());
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderFoodArrayList.size();
    }

    public static class CardViewDesignThingsHolder extends RecyclerView.ViewHolder {
        private CardView cardViewOrderFood = null;
        private ImageView imageViewOrderFood, imageViewOrderFoodAdd = null;
        private TextView textViewOrderFoodName, textViewOrderFoodPrice = null;

        public CardViewDesignThingsHolder(@NonNull View itemView) {
            super(itemView);
            cardViewOrderFood = itemView.findViewById(R.id.cardViewOrderFood);
            imageViewOrderFood = itemView.findViewById(R.id.imageViewOrderFood);
            imageViewOrderFoodAdd = itemView.findViewById(R.id.imageViewOrderFoodAdd);
            textViewOrderFoodName = itemView.findViewById(R.id.textViewOrderFoodName);
            textViewOrderFoodPrice = itemView.findViewById(R.id.textViewOrderFoodPrice);
        }
    }
}
