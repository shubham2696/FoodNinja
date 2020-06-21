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

public class RecyclerViewAdapterCat extends RecyclerView.Adapter<RecyclerViewAdapterCat.ViewHolder>

{
    private  List<Food_Categories> food_categories;
    private  Context context;

    public RecyclerViewAdapterCat(List<Food_Categories> food_categories, Context context) {
        this.food_categories = food_categories;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_categories,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Food_Categories food_category=food_categories.get(i);
        viewHolder.item_type.setText(food_category.getItem_type());
        Picasso.get().load(food_category.getImageurl()).into(viewHolder.imageView);



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

    }

    @Override
    public int getItemCount() {
        return food_categories.size();
    }



    public class ViewHolder  extends RecyclerView.ViewHolder{
        public TextView item_type;
        public ImageView imageView;
        public CardView click;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_type=itemView.findViewById(R.id.food_type);
            imageView = (ImageView) itemView.findViewById(R.id.food_img);
            click=itemView.findViewById(R.id.click);
        }
    }
}