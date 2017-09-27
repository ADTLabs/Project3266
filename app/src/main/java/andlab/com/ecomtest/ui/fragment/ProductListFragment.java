package andlab.com.ecomtest.ui.fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import andlab.com.ecomtest.R;
import andlab.com.ecomtest.ui.PageType;
import andlab.com.ecomtest.ui.activity.BaseDrawerActivity;
import andlab.com.ecomtest.ui.adapter.ItemListAdapter;

/**
 * Created by admin on 7/19/2017.
 */

public class ProductListFragment extends Fragment {


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
                R.layout.fragment_product_list, container, false);
        progressVisibility();


        configList(v,new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false),horizontalList);

        return v;

    }

    Context context;




    void progressVisibility()
    {
        ((BaseDrawerActivity)context).progressVisibility(false);
    }

    void configList(View parent, LinearLayoutManager layoutManager, List<String> horizontalList)
    {
        RecyclerView recyclerView= (RecyclerView)parent.findViewById(R.id.item_listview);
     //   recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recyclerView.setLayoutManager(layoutManager);
        ItemListAdapter horizontalAdapter=new ItemListAdapter(horizontalList, new ItemListAdapter.PostItemListener() {
            @Override
            public void onPostClick(String id) {
                ((BaseDrawerActivity)getActivity()).replaceFragment(new ProductDetailFragment(), PageType.DETAIL);
            }
        });
        recyclerView.setAdapter(horizontalAdapter);
    }

}
