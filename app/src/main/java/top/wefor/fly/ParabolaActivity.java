package top.wefor.fly;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created on 16/7/8.
 *
 * @author ice
 */
public class ParabolaActivity extends AppCompatActivity {

    private float mOldX;
    final float[] mCurrentPosition = new float[2];

    Path mPath = new Path();
    PathMeasure mPathMeasure = new PathMeasure();
    private View mTopView, mBottomView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parabola);

        mTopView = findViewById(R.id.top_view);
        mBottomView = findViewById(R.id.bottom_view);

        mTopView.post(new Runnable() {
            @Override
            public void run() {

                mOldX = mTopView.getX();
                mPath.moveTo(mTopView.getX(), mTopView.getY());
                mPath.quadTo(800, -32, mBottomView.getX(), mBottomView.getY());

                Log.i("xyz ", " xy,xy " + mTopView.getX() + " " + mTopView.getY() + " " + mBottomView.getX() + " " + mBottomView.getY());
                mPathMeasure.setPath(mPath, false);

                final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
                valueAnimator.setDuration(700);
                // 匀速插值器
                LinearInterpolator linearInterpolator = new LinearInterpolator();
                valueAnimator.setInterpolator(linearInterpolator);

                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float value = (Float) valueAnimator.getAnimatedValue();
                        // 获取当前点坐标封装到mCurrentPosition
                        mPathMeasure.getPosTan(value, mCurrentPosition, null);
                        mTopView.setTranslationX(mCurrentPosition[0] - mOldX);
                        mTopView.setTranslationY(mCurrentPosition[1]);

                        Log.i("xxx", "xyz " + mCurrentPosition[0] + " " + mCurrentPosition[1]);
                    }
                });

                valueAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        mBottomView.animate().scaleX(1.5f);
                        mBottomView.animate().scaleY(1.3f);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        mBottomView.animate().scaleX(1f);
                        mBottomView.animate().scaleY(1f);
                        mTopView.animate().translationX(0);
                        mTopView.animate().translationY(0);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });

                valueAnimator.start();

                mTopView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        valueAnimator.start();
                    }
                });
                mBottomView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mTopView.performClick();
                    }
                });

            }
        });


    }

}
