package i.beangate.foodninja;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FoodSingle extends AppCompatActivity {
    String itemtype,imageurl,item_type_id;
    ImageView imgurl;
    Boolean success;
    List<Food_Item> food_items;
    RecyclerViewAdapterFoodList recyclerViewAdapterFoodList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_single);
        recyclerView = (RecyclerView)findViewById(R.id.list_view);
        recyclerView.setHasFixedSize(true);
        /*  RecyclerviewAdapter viewAdapter = new RecyclerviewAdapter( item_models,getContext());*/
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        food_items=new ArrayList<>();
        imgurl=findViewById(R.id.imageView);
        itemtype=getIntent().getExtras().getString("ItemType");
        imageurl=getIntent().getExtras().getString("ItemImage");
        item_type_id=getIntent().getExtras().getString("Item_Type_Id");
        Picasso.get().load(imageurl).into(imgurl);

        Response.Listener<String> responseListener = new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject1 = new JSONObject(response);
                    JSONArray array = jsonObject1.getJSONArray("server_response");
                    /*  Log.d("######", String.valueOf(array));*/
                    for ( int i=0; i< array.length(); i++){

                        JSONObject jsonObject2 = array.getJSONObject(i);
                        Log.d("######", String.valueOf(jsonObject2));
                        Food_Item food_item = new Food_Item(jsonObject2.getString("item_name"),jsonObject2.getDouble("item_price"),jsonObject2.getDouble("item_rating"),jsonObject2.getString("image_url"),jsonObject2.getInt("item_id"),jsonObject2.getString("item_type"),jsonObject2.getInt("item_qty"));
                        food_items.add(food_item);


                    }
                    recyclerViewAdapterFoodList=new RecyclerViewAdapterFoodList(food_items,getApplicationContext());
                    recyclerView.setAdapter(recyclerViewAdapterFoodList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        SaveMyRequest saveMyRequest = new SaveMyRequest(item_type_id,responseListener);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(saveMyRequest);


    }



}

