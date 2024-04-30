package in.amankumar110.foodapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,new FoodCategoryFragment()).commit();


    }


    @Override
    public void onBackPressed() {

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        if (currentFragment instanceof FoodCategoryFragment) {
            super.onBackPressed();
        } else if(currentFragment instanceof FoodDisplayFragment) {
                getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
        }

    }
}