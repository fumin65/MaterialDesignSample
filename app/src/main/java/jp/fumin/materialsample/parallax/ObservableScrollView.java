package jp.fumin.materialsample.parallax;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView {

	private OnScrollChangeListener mScrollChangeListener;

	public ObservableScrollView(Context context) {
		super(context);
	}

	public ObservableScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);

		if (mScrollChangeListener != null) {
			mScrollChangeListener.onScrollChanged(l - oldl, t - oldt);
		}
	}

	public void setScrollChangeListener(OnScrollChangeListener scrollChangeListener) {
		mScrollChangeListener = scrollChangeListener;
	}

	public static interface OnScrollChangeListener {
		void onScrollChanged(int deltaX, int deltaY);
	}
}
