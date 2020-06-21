package i.beangate.foodninja;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SaveMyRequest extends StringRequest {
    private static final String REGISTRATION_REQUEST_URL = "https://beangate.in/food_ninja/app/registration.php";
    private static final String FETCH_TYPE= "https://beangate.in/food_ninja/app/food_item.php";
    private static final String TEMP_ORDER= "https://beangate.in/food_ninja/app/temp_order.php";
    private static final String PER_ORDER= "https://beangate.in/food_ninja/app/per_order.php";

    private Map<String, String> params;

    public SaveMyRequest(String contact,String name, String email,String pass,String user_type,Response.Listener<String> responseListener) {
        super(Method.POST, REGISTRATION_REQUEST_URL,responseListener, null);
        params = new HashMap<>();
        params.put("contact",contact);
        params.put("name",name);
        params.put("email",email);
        params.put("pass",pass);
        params.put("type",user_type);
    }

    public SaveMyRequest(String item_type_id, Response.Listener<String> responseListener) {
        super(Method.POST, FETCH_TYPE,responseListener, null);
        params = new HashMap<>();
        params.put("item_type_id",item_type_id);
    }

    public SaveMyRequest(String contact,String item_id,String totalamt,String itemname ,String itemprice,String itemqty,  Response.Listener<String> responseListener) {
        super(Method.POST, TEMP_ORDER,responseListener, null);
        params = new HashMap<>();
        params.put("contact",contact);
        params.put("item_id",item_id);
        params.put("total_amt", totalamt);
        params.put("item_name",itemname);
        params.put("item_price",itemprice);
        params.put("item_qty", itemqty);
    }
    public SaveMyRequest(String contact,String s, Response.Listener<String> responseListener) {
        super(Method.POST, PER_ORDER,responseListener, null);
        params = new HashMap<>();
        params.put("contact",contact);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
