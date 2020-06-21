package i.beangate.foodninja;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smarteist.autoimageslider.SliderLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment {

    View view;
    private static final String url = "https://beangate.in/food_ninja/app/food_categories.php";
    RecyclerViewAdapterCat recyclerViewAdapterCat;
    RecyclerView recyclerView;
    List<Food_Categories> food_categories;
    public Search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list_view);
        recyclerView.setHasFixedSize(true);
        /*  RecyclerviewAdapter viewAdapter = new RecyclerviewAdapter( item_models,getContext());*/
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        food_categories=new ArrayList<>();
        /* recyclerView.setAdapter(viewAdapter);*/
        loadRecyclerViewData();


        return view;
    }

    private void loadRecyclerViewData() {
        final ProgressDialog dialog = ProgressDialog.show(getContext(), null, "Please Wait");
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            dialog.dismiss();
                            JSONObject jsonObject1 = new JSONObject(response);
                            JSONArray array=jsonObject1.getJSONArray("server_response");
                            Log.d("######", String.valueOf(array));
                            for ( int i=0; i<array.length(); i++){
                                JSONObject jsonObject2 = array.getJSONObject(i);
                                Food_Categories food_category =new Food_Categories(jsonObject2.getInt("item_type_id"),jsonObject2.getString("imageurl"),jsonObject2.getString("item_type"));
                                food_categories.add(food_category);

                            }

                            recyclerViewAdapterCat=new RecyclerViewAdapterCat(food_categories,getContext());
                            recyclerView.setAdapter(recyclerViewAdapterCat);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);

    }
}

