package in.amankumar110.foodapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodCategoryAdaptor extends RecyclerView.Adapter<FoodCategoryAdaptor.FoodHolder> {

    List<FoodCategory> foods;
    FoodItemClickListener clickListener;

    public FoodCategoryAdaptor(List<FoodCategory> foods, FoodItemClickListener  clickListener) {
        this.foods = foods;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_category_item_layout,parent,false);
        return new FoodHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, int position) {

        FoodCategory food = foods.get(position);
        holder.foodImage.setImageResource(food.getImageResource());
        holder.foodName.setText(food.getName());
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public  class FoodHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView foodName;
        ImageView foodImage;
        public FoodHolder(@NonNull View itemView)  {
            super(itemView);

            foodName = itemView.findViewById(R.id.textview);
            foodImage = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            String foodName = (String) ((TextView)v.findViewById(R.id.textview)).getText();
            clickListener.onClick(foodName);

        }
    }
}
