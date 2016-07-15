package top.wefor.fly;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mSelfAnimationTv, mPathTv, mSharedElementsTv;
    LinearLayout mSharedElementsLayout;
    ImageView mSharedElementsIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // lets' begin
        mSelfAnimationTv = (TextView) findViewById(R.id.selfAnimation_tv);
        mPathTv = (TextView) findViewById(R.id.path_tv);

        mSharedElementsLayout = (LinearLayout) findViewById(R.id.sharedElements_layout);
        mSharedElementsTv = (TextView) findViewById(R.id.sharedElements_tv);
        mSharedElementsIv = (ImageView) findViewById(R.id.sharedElements_iv);

        mSelfAnimationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelfAnimation();
            }
        });

        mSharedElementsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedElementsActivity.startThis(MainActivity.this,
                        mSharedElementsIv, R.mipmap.img_huoer,
                        mSharedElementsTv, mSharedElementsTv.getText());
            }
        });

        mPathTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ParabolaActivity.class));
            }
        });
    }

    boolean hasShowingAnimation;

    /*View 自身动画*/
    private void showSelfAnimation() {
        if (hasShowingAnimation)
            mSelfAnimationTv.animate()
                    .rotation(0)
                    .alpha(1f)
                    .translationY(0)
                    .scaleX(1f)
                    .scaleY(1f);
        else
            mSelfAnimationTv.animate()
                    .rotation(90)
                    .alpha(0.5f)
                    .translationY(300)
                    .scaleX(1.3f)
                    .scaleY(1.3f);

        hasShowingAnimation = !hasShowingAnimation;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.action_about))
                    .setMessage("A set of simple animation.")
                    .setPositiveButton("View More", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(getString(R.string.my_jianshu)));
                            startActivity(intent);
                        }
                    })
                    .create().show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
