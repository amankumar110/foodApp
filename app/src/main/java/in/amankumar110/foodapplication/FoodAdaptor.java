package in.amankumar110.foodapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
public class FoodAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Food> foods;
    private Context context;
    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_EMPTY = 1;

    public FoodAdaptor(List<Food> foods, Context context) {
        this.foods = foods;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_layout, parent, false);
            return new FoodHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_list_layout, parent, false);
            return new TextHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FoodHolder) {
            FoodHolder foodHolder = (FoodHolder) holder;
            Food food = foods.get(position);
            foodHolder.foodName.setText(food.getFoodName());
            Glide
                 .with(context)
                 .asBitmap()
                 .load(food.getImageUrl())
                 .encodeFormat(Bitmap.CompressFormat.JPEG)
                 .encodeQuality(10)
                 .into(foodHolder.foodImage);

        } else if (holder instanceof TextHolder) {
            TextHolder textHolder = (TextHolder) holder;
            textHolder.textView.setText("No items found");
        }
    }

    @Override
    public int getItemCount() {
        return foods.isEmpty() ? 1 : foods.size();
    }

    @Override
    public int getItemViewType(int position) {
        return foods.isEmpty() ? VIEW_TYPE_EMPTY : VIEW_TYPE_ITEM;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        // Set LinearLayoutManager if the list is empty
        if (foods.isEmpty()) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
        } else {
            GridLayoutManager layoutManager = new GridLayoutManager(context,2);
            recyclerView.setLayoutManager(layoutManager);
        }
    }
    public static class FoodHolder extends RecyclerView.ViewHolder {
        TextView foodName;
        ImageView foodImage;

        public FoodHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.name);
            foodImage = itemView.findViewById(R.id.image);
        }
    }

    public static class TextHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TextHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }
}
