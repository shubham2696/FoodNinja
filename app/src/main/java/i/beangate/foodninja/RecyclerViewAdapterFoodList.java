package i.beangate.foodninja;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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



public class RecyclerViewAdapterFoodList extends RecyclerView.Adapter<RecyclerViewAdapterFoodList.ViewHolder>

{
    private  List<Food_Item> food_items;
    private  Context context;

    public RecyclerViewAdapterFoodList(List<Food_Item> food_items, Context context) {
        this.food_items = food_items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_single_row,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Food_Item food_item=food_items.get(i);
        // String imageUrl = item_model.getImage_link();
        viewHolder.item.setText(food_item.getItem_name());
        String price= String.valueOf(food_item.getItem_price());
        viewHolder.price.setText(price);
        float rating= (float) food_item.getItem_rating();
        viewHolder.ratingBar.setRating(rating);



        viewHolder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " );
                Intent intent = new Intent(context, Order.class);
                Log.d("!!!!!!!!!!!", String.valueOf(food_item.getItem_id()));
                /*Toast.makeText(this,item_model.getPrice(),Toast.LENGTH_SHORT).show();*/
                String itemid= String.valueOf(food_item.getItem_id());
                String itemprice= String.valueOf(food_item.getItem_price());
                String itemqty= String.valueOf(food_item.getItem_qty());
                intent.putExtra("ItemId",itemid);
                intent.putExtra("ItemName",food_item.getItem_name());
                intent.putExtra("ItemPrice",itemprice);
                intent.putExtra("ItemQty",itemqty);
                intent.putExtra("ItemType",food_item.getItem_type());
                /*Log.d("category_id",item_model.getCategory_id());*/
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return food_items.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        public TextView item;
        public TextView price;
        public RatingBar ratingBar;
        public  Button book;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item=itemView.findViewById(R.id.item);
            price=itemView.findViewById(R.id.price);
            book=itemView.findViewById(R.id.book);
            ratingBar=(RatingBar) itemView.findViewById(R.id.rating);

        }
    }
}