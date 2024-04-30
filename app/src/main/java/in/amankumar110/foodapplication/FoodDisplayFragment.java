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
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;


public class FoodDisplayFragment extends Fragment {

    RecyclerView foodDisplayContainer;

    ImageButton backButton;

    List<Food> foods;
    String foodName;


    FoodDisplayFragment(String foodName) {

        this.foodName = foodName;
        foods = new ArrayList<>();


    }

    FoodDisplayFragment(){
        super();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_food_display, container, false);
        this.foodDisplayContainer = v.findViewById(R.id.foodDisplayContainer);
        this.backButton = v.findViewById(R.id.backButton);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        backButton.setOnClickListener((View v)-> {
            Fragment currentFragment = requireActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

            if (currentFragment instanceof FoodDisplayFragment) {
                requireActivity().getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        FoodDataProvider dataBase = new FoodDataProvider(requireActivity().getApplicationContext(), new FoodDataProvider.onDataLoadedListener() {
            @Override
            public void onDataLoaded(List<Food> data) {
                FoodAdaptor foodAdaptor = new FoodAdaptor(data,requireActivity().getApplicationContext());
                foodDisplayContainer.setAdapter(foodAdaptor);
            }

            @Override
            public void onDataLoadedFailed() {
                FoodAdaptor foodAdaptor = new FoodAdaptor(new ArrayList<>(),requireActivity().getApplicationContext());
                foodDisplayContainer.setAdapter(foodAdaptor);
            }
        });

        dataBase.loadData(foodName);
    }
}