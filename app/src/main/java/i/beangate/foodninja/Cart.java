package i.beangate.foodninja;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    String itemtype,itemid,itemname,itemprice,itemqty,totalamt;
    private static final String url = "https://beangate.in/food_ninja/app/get_temp_order.php";
    RecyclerViewAdapterCart recyclerViewAdapterCart;
    RecyclerView recyclerView;
    List<CartList> cartLists;
    CartList cartList;
    JSONArray array;
    JSONObject jsonObject2,jsonObject1;
    TextView total;
    double total_amt=0;
    String s;
    Button placed;
    Boolean success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = (RecyclerView)findViewById(R.id.list_view);
        placed=findViewById(R.id.placed);
        total=findViewById(R.id.total_amt);
        recyclerView.setHasFixedSize(true);
        /*  RecyclerviewAdapter viewAdapter = new RecyclerviewAdapter( item_models,getContext());*/
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartLists=new ArrayList<>();
        /* recyclerView.setAdapter(viewAdapter);*/
        loadRecyclerViewData();
        //sendData();
        /*try {
            total.setText(jsonObject2.getInt("item_qty")+" X "+jsonObject2.getDouble("item_price"));
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        UserPref user_pref = new UserPref(this);
        final String contact = user_pref.getCont();

        placed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

            /*   builder.setTitle("Message");
                // Setting Dialog Message
                builder.setMessage("You Have Successfully Booked. ");
                builder.setPositiveButton(R.string.label_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Cart.this,Welcome.class);
                        startActivity(intent);
                        finish();
                    }
                });*/

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Log.i("tagconvertstr", "[" + response + "]");
                            JSONObject jsonResponse = new JSONObject(response);
                            success = jsonResponse.getBoolean("success");
                            Log.d("Success==", String.valueOf(success));
                            if (success) {
                               Toast.makeText(getApplicationContext(),"Your order is placed !",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Cart.this,OrderPrep.class);
                                startActivity(intent);

                            } else if (!success) {
                                Snackbar snackbar = Snackbar
                                        .make(view, "Something went wrong", Snackbar.LENGTH_LONG)
                                        .setAction("Retry", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                            }
                                        });
                                snackbar.setActionTextColor(Color.RED);
                                snackbar.show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                };

                SaveMyRequest saveMyRequest = new SaveMyRequest(contact,s, responseListener);

                RequestQueue queue = Volley.newRequestQueue(Cart.this);
                queue.add(saveMyRequest);




            }

        });
    }


    private void loadRecyclerViewData() {
        final ProgressDialog dialog = ProgressDialog.show(this, null, "Please Wait");
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            dialog.dismiss();
                             jsonObject1 = new JSONObject(response);
                             array=jsonObject1.getJSONArray("server_response");
                            Log.d("######", String.valueOf(array));
                            for ( int i=0; i<array.length(); i++){
                                 jsonObject2 = array.getJSONObject(i);

                                 cartList =new CartList(jsonObject2.getInt("item_id"),jsonObject2.getDouble("totalamt"),jsonObject2.getString("item_name"),jsonObject2.getDouble("item_price"),jsonObject2.getInt("item_qty"));
                                cartLists.add(cartList);
                               int qty= jsonObject2.getInt("item_qty");
                              double price=  jsonObject2.getDouble("item_price");
                                total_amt =qty*price+total_amt;

                              total.setText(String.valueOf(total_amt));
                            }

                            recyclerViewAdapterCart =new RecyclerViewAdapterCart(cartLists,getApplicationContext());
                            recyclerView.setAdapter(recyclerViewAdapterCart);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}