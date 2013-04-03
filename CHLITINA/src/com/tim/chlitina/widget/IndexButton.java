package com.tim.chlitina.widget;

import lib.tim.view.OhTextView;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tim.chlitina.R;

public class IndexButton extends RelativeLayout {
	
	private final int TITLE_VIEW_ID = 123321;
	
	private OhTextView mTitleView;
	private ImageView mNewIcon;
	private OhTextView mContentTextView;
	private LayoutParams mContentTextViewLP;
	
	private boolean mHasTitle;
	private String mTitleString;
	private String mContentString;
	private int mLayoutWidth;
	private int mLayoutHeight;

	public IndexButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		loadAttributes(attrs);
		initComponent();
	}
	
	private void loadAttributes(AttributeSet attrs) {
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.IndexButton);
		mHasTitle = a.getBoolean(R.styleable.IndexButton_hasTitle, false);
		mTitleString = a.getString(R.styleable.IndexButton_titleText);
		mContentString = a.getString(R.styleable.IndexButton_contentText);
		mLayoutWidth = a.getDimensionPixelSize(R.styleable.IndexButton_layout_width, 120);
		mLayoutHeight = a.getDimensionPixelSize(R.styleable.IndexButton_layout_height, 121);
		a.recycle();
	}
	
	private void initComponent() {
		final Context context = getContext();
		final Resources res = getResources();
		
		mTitleView = new OhTextView(context);
		mNewIcon = new ImageView(context);
		mContentTextView = new OhTextView(context);
		
		mTitleView.setTextSize(res.getDimensionPixelSize(R.dimen.index_button_title_textSize));
		mTitleView.setBackgroundResource(R.drawable.index_button_title);
		mTitleView.setTypeface(null, Typeface.BOLD);
		mTitleView.setId(TITLE_VIEW_ID);
		
		LayoutParams newLP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		int marginRightOfNew = res.getDimensionPixelSize(R.dimen.index_button_new_icon_marginRight);
		int marginTopOfNew = res.getDimensionPixelSize(R.dimen.index_button_new_icon_marginTop);
		newLP.rightMargin = marginRightOfNew;
		newLP.topMargin = marginTopOfNew;
		newLP.addRule(ALIGN_PARENT_RIGHT);
		mNewIcon.setLayoutParams(newLP);
		mNewIcon.setImageResource(R.drawable.new_icon);
		hideNewIcon();
		
		mContentTextView.setTextSize(res.getDimensionPixelSize(R.dimen.index_button_content_textSize));
		mContentTextViewLP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mContentTextViewLP.addRule(CENTER_IN_PARENT);
		mContentTextView.setLayoutParams(mContentTextViewLP);
		mContentTextView.setTypeface(null, Typeface.BOLD);
		
		RelativeLayout layout = new RelativeLayout(context);
		LayoutParams layoutLP = new LayoutParams(mLayoutWidth, mLayoutHeight);
		layoutLP.addRule(CENTER_IN_PARENT);
		layout.setLayoutParams(layoutLP);
		layout.setBackgroundResource(R.drawable.sl_index_button);
		
		layout.addView(mTitleView);
		layout.addView(mNewIcon);
		layout.addView(mContentTextView);
		
		addView(layout);
		
		setTitleViewVisibility(mHasTitle);
		setTitleText(mTitleString);
		setContentText(mContentString);
	}
	
	public void setContentText(String text) {
		if(mContentTextView != null) {
			mContentTextView.setText(text);
		}
	}
	
	public void setTitleText(String text) {
		if(mTitleView != null) {
			mTitleView.setText(text);
		}
	}
	
	public void showNewIcon() {
		setNewIconVisibility(true);
	}
	
	public void hideNewIcon() {
		setNewIconVisibility(false);
	}
	
	private void setTitleViewVisibility(boolean visible) {
		if(mTitleView != null) {
			mTitleView.setVisibility(visible ? View.VISIBLE : View.GONE);
		}
		if(mContentTextView != null && mContentTextViewLP != null) {
			Resources res = getResources();
			mContentTextViewLP.topMargin = visible ? res.getDimensionPixelSize(R.dimen.index_button_content_marginTop_by_title) : 0;
			mContentTextViewLP.alignWithParent = visible;
			if(visible) {
				mContentTextViewLP.addRule(BELOW, TITLE_VIEW_ID);
			}
			mContentTextView.setTextSize(visible ? 
					res.getDimensionPixelSize(R.dimen.index_button_content_textSize_small) : 
					res.getDimensionPixelSize(R.dimen.index_button_content_textSize));
			mContentTextView.setLayoutParams(mContentTextViewLP);
		}
	}
	
	private void setNewIconVisibility(boolean visible) {
		if(mNewIcon != null) {
			mNewIcon.setVisibility(visible ? View.VISIBLE : View.GONE);
		}
	}

}
