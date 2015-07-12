package jp.fumin.materialsample.parallax;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import jp.fumin.materialsample.R;

public class ParallaxScrollActivity extends ActionBarActivity {

	private Toolbar mToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parallax_scroll);

		Display display = getWindowManager().getDefaultDisplay();
		final Point displaySize = new Point();
		display.getSize(displaySize);

		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		final ImageView backgroundImageView = (ImageView) findViewById(R.id.background_image);

		setToolbarAlpha(0);
		setSupportActionBar(mToolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		final ObservableScrollView scrollView = (ObservableScrollView) findViewById(R.id.scroll_view);
		scrollView.setScrollChangeListener(new ObservableScrollView.OnScrollChangeListener() {
			@Override
			public void onScrollChanged(int deltaX, int deltaY) {

				int scrollY = scrollView.getScrollY();
				int alpha = (int) ((scrollY / (displaySize.y / 2.f)) * 255.f);

				if (alpha < 0) {
					alpha = 0;
				} else if (alpha > 255) {
					alpha = 255;
				}

				setToolbarAlpha(alpha);
				mToolbar.setTranslationY(scrollY);
				backgroundImageView.setTranslationY(scrollY * 0.5f);
			}
		});

		// set top margin as one-third of screen to display background image view.
		LinearLayout contentArea = (LinearLayout) findViewById(R.id.content_area);
		ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) contentArea.getLayoutParams();
		marginParams.setMargins(0, displaySize.y / 2, 0, 0);
	}

	private void setToolbarAlpha(int alpha) {
		mToolbar.getBackground().setAlpha(alpha);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			int color = getWindow().getStatusBarColor();
			getWindow().setStatusBarColor(Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color)));
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
