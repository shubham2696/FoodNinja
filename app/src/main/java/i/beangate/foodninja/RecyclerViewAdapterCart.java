package i.beangate.foodninja;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import static android.content.ContentValues.TAG;

public class RecyclerViewAdapterCart extends RecyclerView.Adapter<RecyclerViewAdapterCart.ViewHolder>
{
    private  List<CartList> cartLists;
    private  Context context;

    public RecyclerViewAdapterCart(List<CartList> cartLists, Context context) {
        this.cartLists = cartLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_row,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final CartList cartList=cartLists.get(i);
        viewHolder.item_food.setText(cartList.getItem_name());
        viewHolder.price_qty.setText(cartList.getItem_qty()+" X "+cartList.getItem_price());
        double total=cartList.getItem_qty()*cartList.getItem_price();


/*
        viewHolder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " );
                Intent intent = new Intent(context, FoodSingle.class);
                Log.d("!!!!!!!!!!!",food_category.getItem_type());
                intent.putExtra("ItemType",food_category.getItem_type());
                String item_type_id= String.valueOf(food_category.getItem_type_id());
                Log.d(" Item ID type: ",item_type_id);
                intent.putExtra("Item_Type_Id",item_type_id);
                intent.putExtra("ItemImage",food_category.getImageurl());
                context.startActivity(intent);

            }
        });
*/

    }

    @Override
    public int getItemCount() {
        return cartLists.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        public TextView item_food,price_qty;
        public Button remove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_food=itemView.findViewById(R.id.item);
            price_qty=itemView.findViewById(R.id.price_qty);
            remove=itemView.findViewById(R.id.remove);

        }
    }
}