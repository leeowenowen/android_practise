package com.orange.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;

import com.orange.R;
import com.orange.util.LayoutParamsUtil;

public class AnimationItemView extends RoundRectFrameLayout
{
    private static final int ITEM_HORIZONTAL_SHADOW = 0;
    private static final int ITEM_VERTICAL_SHADOW = 0;
    // This value is up to the nine-patch image 'selected_bg', should be in
    // pixel
    private static final int SIDELINE_WIDTH_PIXEL =2;

    private FrameLayout mShadowBg = null;
    private RoundRectFrameLayout mFocusedBg = null;
    // all content in #mContentWrapper
   // private RoundRectFrameLayout mRoundRectContainer = null;

    private ScaleAnimation mZoomAnimation;
    private ScaleAnimation mMagnifyAnimation;

    public void setContentView(View v)
    {
    	mFocusedBg.addView(v);
    }

    public AnimationItemView(Context context)
    {
        super(context);
        initComponents(context);
        setupListeners();
        updateTheme();
    }

    private void initComponents(Context context)
    {
        mShadowBg = new FrameLayout(context);
        mShadowBg.setLayoutParams(LayoutParamsUtil.FRAMELAYOUT_MATCH_PARENT);
        mShadowBg.setPadding(ITEM_HORIZONTAL_SHADOW, ITEM_VERTICAL_SHADOW, ITEM_HORIZONTAL_SHADOW, -1);

        mFocusedBg = new RoundRectFrameLayout(context);
        FrameLayout.LayoutParams FRAMELAYOUT_MATCH_PARENT = new FrameLayout.LayoutParams(LayoutParamsUtil.MATCH_PARENT, LayoutParamsUtil.MATCH_PARENT);
        
        mFocusedBg.setLayoutParams(FRAMELAYOUT_MATCH_PARENT);

//        mRoundRectContainer = new RoundRectFrameLayout(context);
//        mRoundRectContainer.setLayoutParams(LayoutParamsUtil.FRAMELAYOUT_MATCH_PARENT);

      //  mFocusedBg.addView(mRoundRectContainer);
        mShadowBg.addView(mFocusedBg);
        addView(mShadowBg);

        mMagnifyAnimation = new ScaleAnimation(1f, 1.3f, 1f, 1.3f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mZoomAnimation = new ScaleAnimation(1.3f, 1f, 1.3f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

    }

    private void startAnimation(boolean focused)
    {
        Animation animation = focused ? mMagnifyAnimation : mZoomAnimation;
        if (null == animation)
        {
            return;
        }
        animation.setDuration(200);
        animation.setFillAfter(true);

        this.startAnimation(animation);
    }

    private void OnFocusedStateChanged(boolean focused)
    {
        startAnimation(focused);
        mFocusedBg.setBackgroundDrawable(focused ? getResources().getDrawable(R.drawable.selected_bg) : null);
        //mFocusedBg.setBackgroundColor(Color.BLUE);
        int focusedBgPadding = focused ? SIDELINE_WIDTH_PIXEL : 0;
        mFocusedBg.setPadding(focusedBgPadding, focusedBgPadding, focusedBgPadding, focusedBgPadding);
    }

    boolean mClicked = false;
    
    public boolean isClickedFlag()
    {
    	return mClicked;
    }

    private void setupListeners()
    {
        // test code
        this.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mClicked = !mClicked;
                OnFocusedStateChanged(mClicked);
            }
        });

        this.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                OnFocusedStateChanged(hasFocus);
            }
        });
    }
    
    

    @SuppressWarnings("deprecation")
    private void updateTheme()
    {
    	//this.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
    	//this.setBackgroundColor(Color.RED);
//    	int radius = 5;
//        float[] outerR = new float[] { 12, 12, 12, 12, 12, 12, 12, 12 }; 
//        RectF   inset = new RectF(10, 10, 10, 10); 
//        float[] innerR = new float[] { 12, 12, 12, 12, 12, 12, 12, 12 };
//        RoundRectShape rrsRectShape = new RoundRectShape(outerR, inset, innerR);
//        ShapeDrawable sDrawable = new ShapeDrawable(rrsRectShape);
//        GradientDrawable gDrawable = new GradientDrawable();
//        gDrawable.se
//       // sDrawable.setColorFilter(Color.RED, Mode.DST_IN);
//        mShadowBg.setBackgroundDrawable(sDrawable);
        //mShadowBg.setBackgroundDrawable(getResources().getDrawable(R.drawable.shadow_bg));
    }
}
