package i.beangate.foodninja;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.SliderView;

public class MainActivity extends AppCompatActivity {
    SliderLayout sliderLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(2); //set scroll delay in seconds :
        setSliderViews();
    }
    private void setSliderViews() {
        for (int i = 0; i <= 2; i++) {
            DefaultSliderView sliderView = new DefaultSliderView(this);

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
                    Toast.makeText(MainActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });
            //at last add this view in your layout:
            sliderLayout.addSliderView(sliderView);
        }
    }
}

