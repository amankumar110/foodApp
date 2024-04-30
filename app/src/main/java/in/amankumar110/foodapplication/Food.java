package in.amankumar110.foodapplication;

public class Food {

    private String imageUrl;
    private String foodName;

    public Food(String imageUrl, String foodName) {
        this.imageUrl = imageUrl;
        this.foodName = foodName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
