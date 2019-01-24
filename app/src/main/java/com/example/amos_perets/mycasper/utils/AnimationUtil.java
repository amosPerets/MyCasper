package com.example.amos_perets.mycasper.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

public class AnimationUtil {

    public static ValueAnimator animateView(View view, long duration, float... values) {
        return ObjectAnimator
                .ofPropertyValuesHolder(view,
                        PropertyValuesHolder.ofFloat("scaleX", values),
                        PropertyValuesHolder.ofFloat("scaleY", values))
                .setDuration(duration);
    }

    @SuppressLint("WrongConstant")
    public static ValueAnimator animateStringsArray(View view, long duration, String... values) {
        ValueAnimator valueAnimator = null;
        if(view instanceof TextView){
            final int[] index = {1};
            TextView textView = (TextView)view;
            textView.setText(values[0]);
            valueAnimator = ObjectAnimator
                    .ofPropertyValuesHolder(view,
                            PropertyValuesHolder.ofFloat("alpha", 1))
                    .setDuration(duration);
            valueAnimator.setRepeatMode(Animation.REVERSE);
            valueAnimator.setRepeatCount(Animation.INFINITE);
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationRepeat(Animator animation) {
                    textView
                            .animate()
                            .alpha(0)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    textView.setText(values[index[0] % values.length]);
                                    index[0]++;
                                }
                            }).start();

                }

            });
            valueAnimator.start();
        }

        return valueAnimator;
    }
}
