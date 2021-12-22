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

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.CardViewDesignThingsHolder>{
    private Context context;
    private ArrayList<OrderFood> basketArrayList = new ArrayList<>();

    public BasketAdapter(Context context, ArrayList<OrderFood> basketArrayList) {
        this.context = context;
        this.basketArrayList = basketArrayList;
    }

    @NonNull
    @Override
    public CardViewDesignThingsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.basket, parent, false);
        return new CardViewDesignThingsHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CardViewDesignThingsHolder holder, int position) {
        OrderFood orderFood = basketArrayList.get(position);

        Picasso.get()
                .load(Uri.parse(orderFood.getImageurl()))
                .into(holder.imageViewBasketFood);

        holder.textViewBasketFoodName.setText(orderFood.getName());
        holder.textViewBasketPrice.setText("("+orderFood.getPrice()+" TL)");

        holder.imageViewBasketSubtract.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                final DatabaseBasketCreator databaseBasketCreator = new DatabaseBasketCreator(context);
                new BasketDao().deleteFoodBasket(databaseBasketCreator, orderFood.getId());
                basketArrayList.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return basketArrayList.size();
    }


    public static class CardViewDesignThingsHolder extends RecyclerView.ViewHolder {
        private CardView cardViewBasket = null;
        private ImageView imageViewBasketFood, imageViewBasketSubtract = null;
        private TextView textViewBasketFoodName, textViewBasketPrice = null;

        public CardViewDesignThingsHolder(@NonNull View itemView) {
            super(itemView);
            cardViewBasket = itemView.findViewById(R.id.cardViewBasket);
            imageViewBasketFood = itemView.findViewById(R.id.imageViewBasketFood);
            imageViewBasketSubtract = itemView.findViewById(R.id.imageViewBasketSubtract);
            textViewBasketFoodName = itemView.findViewById(R.id.textViewBasketFoodName);
            textViewBasketPrice = itemView.findViewById(R.id.textViewBasketPrice);
        }
    }
}
