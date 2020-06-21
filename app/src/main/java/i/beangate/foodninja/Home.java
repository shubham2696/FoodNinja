package i.beangate.foodninja;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.SliderView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    View view;
    SliderLayout sliderLayout;
    private static final String url = "https://beangate.in/food_ninja/app/fetch_trending.php";
    RecyclerviewAdapter recyclerviewAdapter;
    RecyclerView recyclerView;
    List<Item_Model> item_models;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      view=  inflater.inflate(R.layout.fragment_home, container, false);
        sliderLayout = view.findViewById(R.id.imageSlider);
        //slider
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(2); //set scroll delay in seconds :
        setSliderViews();


        recyclerView = (RecyclerView) view.findViewById(R.id.list_view);
        recyclerView.setHasFixedSize(true);
        /*  RecyclerviewAdapter viewAdapter = new RecyclerviewAdapter( item_models,getContext());*/
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        item_models=new ArrayList<>();
        /* recyclerView.setAdapter(viewAdapter);*/
        loadRecyclerViewData();


        return view;
    }
    private void setSliderViews() {
        for (int i = 0; i <= 2; i++) {
            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i)
            {
                case 0:
                    sliderView.setImageUrl("https://i.ndtvimg.com/i/2016-07/tofu-rice-625_625x350_71467969111.jpg?auto=compress&cs=tinysrgb&dpr=2&h=100&w=1260");
                    break;
                case 1:
                    sliderView.setImageUrl("https://i.ndtvimg.com/i/2016-06/indian-food-625_625x350_71467111221.jpg?auto=compress&cs=tinysrgb&h=100&w=1260");
                    break;
                case 2:
                    sliderView.setImageUrl("https://media.proprofs.com/images/QM/user_images/1754155/1504169362.jpg?auto=compress&cs=tinysrgb&dpr=2&h=100&w=1260");
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
           /* sliderView.setDescription("The quick brown fox jumps over the lazy dog.\n" +
                    "Jackdaws love my big sphinx of quartz. " + (i + 1));*/
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(getContext(), "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });
            //at last add this view in your layout:
            sliderLayout.addSliderView(sliderView);
        }
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
                                Item_Model item_model =new Item_Model(jsonObject2.getString("name"),jsonObject2.getString("type"),jsonObject2.getString("imageurl"),jsonObject2.getDouble("item_rating"));
                                item_models.add(item_model);

                            }

                            recyclerviewAdapter=new RecyclerviewAdapter(item_models,getContext());
                            recyclerView.setAdapter(recyclerviewAdapter);
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
