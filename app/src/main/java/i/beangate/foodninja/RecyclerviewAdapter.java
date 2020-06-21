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

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>

{
private  List<Item_Model> item_models;
private  Context context;

    public RecyclerviewAdapter(List<Item_Model> item_models, Context context) {
        this.item_models = item_models;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.treanding_row,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Item_Model item_model=item_models.get(i);
       // String imageUrl = item_model.getImage_link();
        viewHolder.item.setText(item_model.getName());
        viewHolder.item_type.setText(item_model.getType());
        float rating= (float) item_model.getRating();
        viewHolder.ratingBar.setRating(rating);
       Picasso.get().load(item_model.getImageurl()).into(viewHolder.imageView);



        viewHolder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " );
                Intent intent = new Intent(context, FoodSingle.class);
                Log.d("!!!!!!!!!!!",item_model.getType());
                intent.putExtra("ItemType",item_model.getType());
                intent.putExtra("ItemImage",item_model.getImageurl());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return item_models.size();
    }



    public class ViewHolder  extends RecyclerView.ViewHolder{
        public TextView   item;
        public TextView item_type;
        public ImageView imageView;
        public RatingBar ratingBar;
        public CardView click;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item=itemView.findViewById(R.id.item);
            item_type=itemView.findViewById(R.id.item_type);
            imageView = (ImageView) itemView.findViewById(R.id.food_img);
            ratingBar=(RatingBar) itemView.findViewById(R.id.rating);
            click=itemView.findViewById(R.id.click);
        }
    }
}