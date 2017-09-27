package andlab.com.ecomtest.item;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi Prakash on 23-07-2017.
 */

public class Product {
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("price")
    String Price;
    @SerializedName("regular_price")
    String RPrice;
    @SerializedName("sale_price")
    String Sprice;
    @SerializedName("images")
    List<String> src=new ArrayList<>();


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return Price;
    }

    public String getRPrice() {
        return RPrice;
    }

    public String getSprice() {
        return Sprice;
    }

    public List<String> getSrc() {
        return src;
    }

    public String getTranslatedText() {
        return id;
    }
}
