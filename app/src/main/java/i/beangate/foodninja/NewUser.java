package i.beangate.foodninja;

import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import org.json.JSONException;
import org.json.JSONObject;

public class NewUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
EditText uname,uemail,upass;
    private AwesomeValidation awesomeValidation;
    String name,email,sessionId,pass,contact="9589412550",user_type;
    Button btn_save;
    Boolean success;
    Spinner userType;
    NetworkInfo ninfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
       /* tv=findViewById(R.id.tview);*/
         sessionId= getIntent().getStringExtra("contact");
         btn_save=findViewById(R.id.save);
        uname=findViewById(R.id.name);
        userType=findViewById(R.id.user_type);
        uemail=findViewById(R.id.email);
        upass=findViewById(R.id.pass);
      //  Log.d("3333333333",sessionId);
     /*   tv.setText(sessionId);*/


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.name, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS, R.string.emailerror);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.usertype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(adapter);
        userType.setOnItemSelectedListener(this);
        //registraion
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (awesomeValidation.validate()) {

                    // internet conectivity
                    ConnectivityManager cm = (ConnectivityManager)
                            getSystemService(CONNECTIVITY_SERVICE);
                    NetworkInfo ninfo = cm.getActiveNetworkInfo();
                    if (ninfo != null && ninfo.isConnected()) {
                        name = uname.getText().toString().trim();
                        email = uemail.getText().toString().trim();
                        pass = upass.getText().toString().trim();



                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    Log.i("tagconvertstr", "[" + response + "]");
                                    JSONObject jsonResponse = new JSONObject(response);
                                    success = jsonResponse.getBoolean("success");
                                        Log.d("Success==", String.valueOf(success));
                                    if (success) {
                                        UserPref user_pref = new UserPref(NewUser.this);
                                        user_pref.setCont(sessionId);
                                        user_pref.setName(name);

                                      /*  user_pref.set(sessionId);*/
                                        Intent intent = new Intent(NewUser.this, Welcome.class);
                                        NewUser.this.startActivity(intent);
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
                        SaveMyRequest saveMyRequest = new SaveMyRequest(sessionId,name, email,pass,user_type, responseListener);

                        RequestQueue queue = Volley.newRequestQueue(NewUser.this);
                        queue.add(saveMyRequest);
                    } else
                        Snackbar.make(view, " no Internet connection aviable", Snackbar.LENGTH_SHORT).show();


                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       String text = parent.getItemAtPosition(position).toString();
        if (position > 0) {
            user_type=text;
            Toast.makeText(getApplicationContext(), "Selected:" + user_type, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
