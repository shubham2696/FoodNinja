package i.beangate.foodninja;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Order extends AppCompatActivity  {
    String itemtype,itemid,itemname,itemprice,itemqty;
    TextView item,price,total_amt;
    EditText qty;
    double totalAmt=0;
    Button add;
    ImageView minus,plus;
    int count=1;
    Boolean success;
    View view;
    String selectqty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        UserPref user_pref = new UserPref(this);
        final String contact = user_pref.getCont();

        itemtype=getIntent().getExtras().getString("ItemType");
        itemid=getIntent().getExtras().getString("ItemId");
        itemname=getIntent().getExtras().getString("ItemName");
        itemprice=getIntent().getExtras().getString("ItemPrice");
        itemqty=getIntent().getExtras().getString("ItemQty");
        final double itemPrice= Double.parseDouble(itemprice);
        item=findViewById(R.id.item);
        price=findViewById(R.id.price);
        qty=findViewById(R.id.qty);
        add=findViewById(R.id.add);
        minus=findViewById(R.id.minus);
        plus=findViewById(R.id.plus);

        item.setText(itemname);
        price.setText(itemprice);
         //String s = String.valueOf(total_amt);
       // total_amt.setText(s);

       // add.setText("To Pay "+s);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                if(count<0)
                    count=0;
                qty.setText(String.valueOf(count));
                selectqty=  qty.getText().toString();
                totalAmt= count*itemPrice;
                add.setText("To Pay "+String.valueOf(totalAmt));
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                qty.setText(String.valueOf(count));
                selectqty=  qty.getText().toString();
                totalAmt= count*itemPrice;
                add.setText("Add to cart"+String.valueOf(totalAmt));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Log.i("tagconvertstr", "[" + response + "]");
                            JSONObject jsonResponse = new JSONObject(response);
                            success = jsonResponse.getBoolean("success");
                            Log.d("Success==", String.valueOf(success));
                            if (success) {


                                Intent intent = new Intent(Order.this, Cart.class);

                             /*   intent.putExtra("ItemType",itemtype);
                                intent.putExtra("ItemId",itemid);
                                intent.putExtra("ItemName",itemname);
                                intent.putExtra("ItemPrice",itemprice);
                                intent.putExtra("ItemQty",itemqty);
                                intent.putExtra("TotalAmt",totalAmt);*/

                                startActivity(intent);
                                finish();
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
                String totalamount= String.valueOf(totalAmt);
                SaveMyRequest saveMyRequest = new SaveMyRequest(contact,itemid,totalamount,itemname,itemprice,selectqty, responseListener);

                RequestQueue queue = Volley.newRequestQueue(Order.this);
                queue.add(saveMyRequest);

            }
        });

        Log.d("value of count =", String.valueOf(count));
    }


}
