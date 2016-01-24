package org.hyochan.godlotto.utils.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.hyochan.godlotto.R;

public class RollingItem extends FrameLayout {

	private final int maxNum = 45;
	private final int minNum = 1;

	private LayoutInflater inflater;
	private Animation appear, disappear;
	private ViewGroup container;

	private View[] viewPool;
	private int currView, nextView;
	private int countNext = 1;
	private int repeatTime = 0;

	public RollingItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public RollingItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public RollingItem(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context) {
		inflate(context, R.layout.rolling_item, this);
		this.inflater = LayoutInflater.from(context);
		appear = AnimationUtils.loadAnimation(context, R.anim.up_appear);
		disappear = AnimationUtils.loadAnimation(context, R.anim.up_disappear);
		
		container = (ViewGroup) findViewById(R.id.frame_roll_container);
		
		appear.setFillAfter(true);
		disappear.setFillAfter(true);
		appear.setAnimationListener(onAnimationAppear);
		
		initViewPool(2);
	}
	
	private View createView(int num) {
		TextView txtView = (TextView) inflater.inflate(R.layout.rolling_item_number, this, false);
		txtView.setText(String.valueOf(num));
		return txtView;
	}
	
	private void initViewPool(int count) {
		viewPool = new View[count];
		
		for(int i = 0; i < count; i++) {
			viewPool[i] = createView(i);
			
			container.addView(viewPool[i]);
		}
		
		currView = 0;
		nextView = 1;
	}
	
	public void roll(int count) {
		repeatTime = count + 1;
		countNext = 1;
		
		doNextMove();
	}
	
	private void resetDuration() {
		if(10 < repeatTime) {
			appear.setDuration(50);
			disappear.setDuration(50);
		} else if(4 < repeatTime) {
			appear.setDuration(70);
			disappear.setDuration(70);
		} else if(2 < repeatTime) {
			appear.setDuration(120);
			disappear.setDuration(120);
		}  else if(0 < repeatTime) {
			appear.setDuration(150);
			disappear.setDuration(150);
		}
	}
	
	private AnimationListener onAnimationAppear = new AnimationListener() {
		
		@Override
		public void onAnimationStart(Animation animation) {
		}
		
		@Override
		public void onAnimationRepeat(Animation animation) {
		}
		
		@Override
		public void onAnimationEnd(Animation animation) {
			postDelayed(new Runnable() {
				
				@Override
				public void run() {
					doNextMove();
				}
			}, 1);
		}
	};

	private void doNextMove()
	{
		countNext++;
		if(maxNum < countNext)
			countNext = 1;
		repeatTime--;
		if(minNum > repeatTime) {
			repeatTime = 0;
			return;
		}
		resetDuration();


		TextView txtCurrent = (TextView) getCurrentView();
		txtCurrent.startAnimation(disappear);

		TextView txtNext = (TextView) getNextView();
		txtNext.startAnimation(appear);
		txtNext.setVisibility(View.VISIBLE);

		int countAppear = countNext - 1;
		if(countAppear == 0)
			countAppear = 45;
		txtNext.setText(String.valueOf(countAppear));

		if(countNext > 1 && countNext <= 10)
			txtNext.setBackgroundResource(R.drawable.shape_roll_ones);
		else
		if(countNext > 10 && countNext <= 20)
			txtNext.setBackgroundResource(R.drawable.shape_roll_tens);
		else
		if(countNext > 20 && countNext <= 30)
			txtNext.setBackgroundResource(R.drawable.shape_roll_twenties);
		else
		if(countNext > 30 && countNext <= 40)
			txtNext.setBackgroundResource(R.drawable.shape_roll_thirties);
		else if(countNext > 40 && countNext <= 45 || countNext == 1)
			txtNext.setBackgroundResource(R.drawable.shape_roll_fourties);

		next();
	}
	
	private View getCurrentView() {
		View v = viewPool[currView];
		return v;
	}
	
	private View getNextView() {
		View v = viewPool[nextView];
		return v;
	}
	
	private void next() {
		int length = viewPool.length;
		currView = nextView;
		nextView++;
		if(length <= nextView) {
			nextView = 0;
		}
	}
	
	
	 
	
}
