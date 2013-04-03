package com.tim.chlitina.widget;

import lib.tim.view.OhTextView;
import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;

public class ItemScrollForeverTextView extends OhTextView {
	
	private boolean isAutoScrollable;

	@Override
	public boolean isFocused() {
		return isAutoScrollable;
	}

	public ItemScrollForeverTextView(Context context) {
		super(context);
		initComponent();
	}

	public ItemScrollForeverTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initComponent();
	}

	public ItemScrollForeverTextView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		initComponent();
	}
	
	private void initComponent() {
		isAutoScrollable = true;
		setAutoScrollable(isAutoScrollable);
	}
	
	public void setAutoScrollable(boolean scrollable) {
		isAutoScrollable = scrollable;
		setSingleLine(true);
		setEllipsize(scrollable ? TruncateAt.MARQUEE : TruncateAt.END);
		setHorizontallyScrolling(scrollable);
		setMarqueeRepeatLimit(scrollable ? -1 : 0);
	}
}
