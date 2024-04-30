package in.amankumar110.foodapplication;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class FoodDataProvider {

     interface  onDataLoadedListener {
         void onDataLoaded(List<Food> data);
         void onDataLoadedFailed();
     }

    private List<Food> foodData;
    private Context context;
    private onDataLoadedListener onDataLoadedListener;


    public FoodDataProvider(Context context, onDataLoadedListener onDataLoadedListener ) {

        this.context = context;
        this.onDataLoadedListener = onDataLoadedListener;
    }

    public void loadData(String query) {

        foodData = new ArrayList<>();

        String url = "https://api.spoonacular.com/recipes/random";
        String key = "4eba78a8a05c4c0aa810006da26eb960";
        String requestUrl = url+"?tags="+query+"&number=5&apiKey="+key;

        Log.e("API",requestUrl);

        StringRequest request = new StringRequest(requestUrl, s -> {

            FoodJson foodJson= new GsonBuilder().create().fromJson(s, FoodJson.class);


            for(Recipe recipe : foodJson.getRecipes()) {
                foodData.add(new Food(recipe.getImage(),recipe.getTitle()));
            }

            onDataLoadedListener.onDataLoaded(foodData);



        }, volleyError -> {
            onDataLoadedListener.onDataLoadedFailed();
        });

        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    public List<Food> getFoodData() {

        return this.foodData;
    }
}
