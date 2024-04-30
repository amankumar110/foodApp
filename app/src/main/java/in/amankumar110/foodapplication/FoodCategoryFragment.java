package in.amankumar110.foodapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class FoodCategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_food_category, container, false);
        recyclerView = v.findViewById(R.id.foodRecyclerView);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        List<FoodCategory> foods = new ArrayList<>();
        foods.add(new FoodCategory("burgers", R.drawable.burgers));
        foods.add(new FoodCategory("pizzas", R.drawable.pizzas));
        foods.add(new FoodCategory("pancakes", R.drawable.pancakes));
        foods.add(new FoodCategory("desert", R.drawable.deserts));

        FoodCategoryAdaptor foodCategoryAdaptor = new FoodCategoryAdaptor(foods, (String foodName) -> {

            requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new FoodDisplayFragment(foodName)).commit();
        });
        recyclerView.setAdapter(foodCategoryAdaptor);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }
}