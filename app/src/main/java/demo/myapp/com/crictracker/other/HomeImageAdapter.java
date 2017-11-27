package demo.myapp.com.crictracker.other;

/**
 * Created by eghambaram on 14/11/17.
 */


        import android.content.Context;
        import android.support.v4.view.PagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;

        import java.security.PublicKey;

        import demo.myapp.com.crictracker.R;

public class HomeImageAdapter extends PagerAdapter {
     Context mContext;

    public HomeImageAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return sliderImagesId.length;
    }

    private int[] sliderImagesId = new int[]{
            R.drawable.app_icon, R.drawable.home_icon1, R.drawable.home_icon2,
            R.drawable.app_icon, R.drawable.home_icon1, R.drawable.home_icon2,
    };

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(mContext);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView.setImageResource(sliderImagesId[i]);
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }
}