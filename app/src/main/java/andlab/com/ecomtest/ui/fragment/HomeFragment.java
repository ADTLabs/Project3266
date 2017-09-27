package andlab.com.ecomtest.ui.fragment;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import andlab.com.ecomtest.R;
import andlab.com.ecomtest.helper.Requester;
import andlab.com.ecomtest.item.Product;
import andlab.com.ecomtest.ui.activity.BaseDrawerActivity;
import andlab.com.ecomtest.ui.adapter.HomePageItemAdapter;
import andlab.com.ecomtest.ui.adapter.SliderAdapter;
import andlab.com.ecomtest.ui.PageType;

/**
 * Created by admin on 7/19/2017.
 */

public class HomeFragment extends Fragment {
    private static String TAG="HomeFragment";
    int[] mResources = {
            R.drawable.first,
            R.drawable.second,
            R.drawable.third,
            R.drawable.fourth,
            R.drawable.fifth,
            R.drawable.sixth
    };
    String[] itemname ={
            "Safari",
            "Camera",
            "Global",
            "FireFox",
            "UC Browser",
            "Android Folder",
            "VLC Player",
            "Cold War"
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context=getActivity();
        List<String> horizontalList =new ArrayList<>();
        for (String val:itemname)
        {
            horizontalList.add(val);
        }
        View v=inflater.inflate(
                R.layout.fragment_home, container, false);
        progressVisibility();
        configSlider(v);


        configList(v.findViewById(R.id.layout1),new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false),horizontalList);

        configList(v.findViewById(R.id.layout2),new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false),horizontalList);

        configList(v.findViewById(R.id.layout3),new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false),horizontalList);

//        configList(v.findViewById(R.id.layout4),new LinearLayoutManager(context,
//                LinearLayoutManager.HORIZONTAL, false),horizontalList);
        getData();
        return v;

    }

    Context context;

    private void getData()
    {
        String url = Requester.createReqUrl("products");
        Log.d(TAG, "requ"+url);
        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();

        RequestQueue rq = Volley.newRequestQueue(context);
        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                parse(response);
                pDialog.hide();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

        rq.add(strReq);
    }


    void configSlider(View v)
    {
        ViewPager viewPager = (ViewPager) v.findViewById(R.id.view_pager);

        final ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        layoutParams.width =  android.view.ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height =  android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM;

        SliderAdapter mCustomPagerAdapter = new SliderAdapter(context,mResources);
        viewPager.setAdapter(mCustomPagerAdapter);
    }

    void progressVisibility()
    {
        ((BaseDrawerActivity)context).progressVisibility(false);
    }

    void configList(View parent, LinearLayoutManager layoutManager, List<String> horizontalList)
    {
        LinearLayout ll1=(LinearLayout) parent;
        RecyclerView horizontal_recycler_view1= (RecyclerView)ll1. findViewById(R.id.list_view);
        horizontal_recycler_view1.setLayoutManager(layoutManager);
        HomePageItemAdapter horizontalAdapter=new HomePageItemAdapter(horizontalList, new HomePageItemAdapter.PostItemListener() {
            @Override
            public void onPostClick(String id) {
                ((BaseDrawerActivity)getActivity()).replaceFragment(new ProductListFragment(), PageType.LIST);
            }
        });
        horizontal_recycler_view1.setAdapter(horizontalAdapter);
    }


    public void parse(String jsonLine) {
//        JsonElement jelement = new JsonParser().parse(jsonLine);
////        JsonObject  jobject = jelement.getAsJsonObject();
////        jobject = jobject.getAsJsonObject("data");
//        JsonArray jarray = jelement.getAsJsonArray();
//        jobject = jarray.get(0).getAsJsonObject();
//        String result = jobject.get("translatedText").toString();
//        return result;


        Gson gson = new Gson();
        Product product = gson.fromJson(jsonLine, Product.class);

        System.out.println("Translated text: " + product.getTranslatedText());
    }
}
