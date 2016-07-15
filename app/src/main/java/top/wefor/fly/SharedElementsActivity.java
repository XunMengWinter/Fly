package top.wefor.fly;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created on 16/7/15.
 *
 * @author ice
 */
public class SharedElementsActivity extends AppCompatActivity {
    public static final String IMAGE_RES_ID = "image_red_id";
    public static final String TEXT = "text";
    public static final String TRANSIT_IMAGE = "transit_image";
    public static final String TRANSIT_TEXT = "transit_text";


    ImageView mIv;
    TextView mTv;

    public static void startThis(Context context, View imageView, @DrawableRes int imageResId, View textView, CharSequence text) {
        Intent intent = new Intent(context, SharedElementsActivity.class);
        intent.putExtra(IMAGE_RES_ID, imageResId);
        intent.putExtra(TEXT, text);
        // core
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,
                        Pair.create(imageView, TRANSIT_IMAGE), Pair.create(textView, TRANSIT_TEXT));
        try {
            ActivityCompat.startActivity((Activity) context, intent, optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_elements);
        mIv = (ImageView) findViewById(R.id.iv);
        mTv = (TextView) findViewById(R.id.tv);

        int imageResId = getIntent().getIntExtra(IMAGE_RES_ID, 0);
        if (imageResId != 0) {
            ViewCompat.setTransitionName(mIv, TRANSIT_IMAGE);
            mIv.setImageResource(imageResId);
        }

        String title = getIntent().getStringExtra(TEXT);
        if (title != null) {
            ViewCompat.setTransitionName(mTv, TRANSIT_TEXT);
            mTv.setText(title);
        }
    }
}
