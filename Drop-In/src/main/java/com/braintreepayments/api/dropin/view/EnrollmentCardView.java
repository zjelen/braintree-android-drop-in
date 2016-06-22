package com.braintreepayments.api.dropin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ViewAnimator;

import com.braintreepayments.api.dropin.R;
import com.braintreepayments.api.dropin.interfaces.AddPaymentUpdateListener;

public class EnrollmentCardView extends RelativeLayout {

    private EditText mSmsCode;
    private ViewAnimator mViewAnimator;
    private Button mConfirm;
    private ProgressBar mProgressBar;

    private AddPaymentUpdateListener mListener;

    public EnrollmentCardView(Context context) {
        super(context);
        init(context);
    }

    public EnrollmentCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EnrollmentCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        if(isInEditMode()) {
            return;
        }
        LayoutInflater.from(context).inflate(R.layout.bt_enrollment_card, this, true);
        mSmsCode = (EditText)findViewById(R.id.sms_code);
        mViewAnimator = (ViewAnimator)findViewById(R.id.view_animator);
        mConfirm = (Button)findViewById(R.id.confirm_button);
        mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);

        Animation fadeIn = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out);
        mViewAnimator.setInAnimation(fadeIn);
        mViewAnimator.setOutAnimation(fadeOut);
        mConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewAnimator.showNext();
                if (mListener != null) {
                    mListener.onPaymentUpdated(EnrollmentCardView.this);
                }
            }
        });
    }

    public void setAddPaymentUpdatedListener(AddPaymentUpdateListener listener) {
        mListener = listener;
    }

    public String getSmsCode() {
        return mSmsCode.getText().toString();
    }
}
