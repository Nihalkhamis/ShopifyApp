// Generated by view binder compiler. Do not edit!
package com.gradle.shopifyapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.gradle.shopifyapp.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentOrderConfirmationBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CircleImageView addressBtn;

  @NonNull
  public final TextView addressTitle;

  @NonNull
  public final ImageView backBtn;

  @NonNull
  public final ConstraintLayout bodyLayout;

  @NonNull
  public final ConstraintLayout bottomLayout;

  @NonNull
  public final View bottomView;

  @NonNull
  public final RadioButton cashRadioBtn;

  @NonNull
  public final EditText couponEditText;

  @NonNull
  public final TextView couponTextview;

  @NonNull
  public final RadioButton creditRadioBtn;

  @NonNull
  public final TextView discount;

  @NonNull
  public final TextView discountTv;

  @NonNull
  public final TextView emailTextView;

  @NonNull
  public final View firstLine;

  @NonNull
  public final View fourthLine;

  @NonNull
  public final View line;

  @NonNull
  public final TextView nameTextView;

  @NonNull
  public final Button orderBtn;

  @NonNull
  public final CircleImageView paymentBtn;

  @NonNull
  public final TextView paymentMethodTextview;

  @NonNull
  public final RadioGroup paymentRg;

  @NonNull
  public final TextView phoneTextView;

  @NonNull
  public final ProgressBar progressbar;

  @NonNull
  public final TextView shipping;

  @NonNull
  public final TextView shippingFeeTv;

  @NonNull
  public final TextView shippingTxt;

  @NonNull
  public final TextView shippingTxtInput;

  @NonNull
  public final TextView subtotal;

  @NonNull
  public final TextView subtotalTv;

  @NonNull
  public final View thirdLine;

  @NonNull
  public final View topView;

  @NonNull
  public final TextView totalPriceLabel;

  @NonNull
  public final TextView totalPriceTextview;

  @NonNull
  public final ImageView verify;

  private FragmentOrderConfirmationBinding(@NonNull ConstraintLayout rootView,
      @NonNull CircleImageView addressBtn, @NonNull TextView addressTitle,
      @NonNull ImageView backBtn, @NonNull ConstraintLayout bodyLayout,
      @NonNull ConstraintLayout bottomLayout, @NonNull View bottomView,
      @NonNull RadioButton cashRadioBtn, @NonNull EditText couponEditText,
      @NonNull TextView couponTextview, @NonNull RadioButton creditRadioBtn,
      @NonNull TextView discount, @NonNull TextView discountTv, @NonNull TextView emailTextView,
      @NonNull View firstLine, @NonNull View fourthLine, @NonNull View line,
      @NonNull TextView nameTextView, @NonNull Button orderBtn, @NonNull CircleImageView paymentBtn,
      @NonNull TextView paymentMethodTextview, @NonNull RadioGroup paymentRg,
      @NonNull TextView phoneTextView, @NonNull ProgressBar progressbar, @NonNull TextView shipping,
      @NonNull TextView shippingFeeTv, @NonNull TextView shippingTxt,
      @NonNull TextView shippingTxtInput, @NonNull TextView subtotal, @NonNull TextView subtotalTv,
      @NonNull View thirdLine, @NonNull View topView, @NonNull TextView totalPriceLabel,
      @NonNull TextView totalPriceTextview, @NonNull ImageView verify) {
    this.rootView = rootView;
    this.addressBtn = addressBtn;
    this.addressTitle = addressTitle;
    this.backBtn = backBtn;
    this.bodyLayout = bodyLayout;
    this.bottomLayout = bottomLayout;
    this.bottomView = bottomView;
    this.cashRadioBtn = cashRadioBtn;
    this.couponEditText = couponEditText;
    this.couponTextview = couponTextview;
    this.creditRadioBtn = creditRadioBtn;
    this.discount = discount;
    this.discountTv = discountTv;
    this.emailTextView = emailTextView;
    this.firstLine = firstLine;
    this.fourthLine = fourthLine;
    this.line = line;
    this.nameTextView = nameTextView;
    this.orderBtn = orderBtn;
    this.paymentBtn = paymentBtn;
    this.paymentMethodTextview = paymentMethodTextview;
    this.paymentRg = paymentRg;
    this.phoneTextView = phoneTextView;
    this.progressbar = progressbar;
    this.shipping = shipping;
    this.shippingFeeTv = shippingFeeTv;
    this.shippingTxt = shippingTxt;
    this.shippingTxtInput = shippingTxtInput;
    this.subtotal = subtotal;
    this.subtotalTv = subtotalTv;
    this.thirdLine = thirdLine;
    this.topView = topView;
    this.totalPriceLabel = totalPriceLabel;
    this.totalPriceTextview = totalPriceTextview;
    this.verify = verify;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentOrderConfirmationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentOrderConfirmationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_order_confirmation, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentOrderConfirmationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.address_btn;
      CircleImageView addressBtn = ViewBindings.findChildViewById(rootView, id);
      if (addressBtn == null) {
        break missingId;
      }

      id = R.id.address_title;
      TextView addressTitle = ViewBindings.findChildViewById(rootView, id);
      if (addressTitle == null) {
        break missingId;
      }

      id = R.id.back_btn;
      ImageView backBtn = ViewBindings.findChildViewById(rootView, id);
      if (backBtn == null) {
        break missingId;
      }

      id = R.id.body_layout;
      ConstraintLayout bodyLayout = ViewBindings.findChildViewById(rootView, id);
      if (bodyLayout == null) {
        break missingId;
      }

      id = R.id.bottom_layout;
      ConstraintLayout bottomLayout = ViewBindings.findChildViewById(rootView, id);
      if (bottomLayout == null) {
        break missingId;
      }

      id = R.id.bottom_view;
      View bottomView = ViewBindings.findChildViewById(rootView, id);
      if (bottomView == null) {
        break missingId;
      }

      id = R.id.cash_radioBtn;
      RadioButton cashRadioBtn = ViewBindings.findChildViewById(rootView, id);
      if (cashRadioBtn == null) {
        break missingId;
      }

      id = R.id.coupon_editText;
      EditText couponEditText = ViewBindings.findChildViewById(rootView, id);
      if (couponEditText == null) {
        break missingId;
      }

      id = R.id.coupon_textview;
      TextView couponTextview = ViewBindings.findChildViewById(rootView, id);
      if (couponTextview == null) {
        break missingId;
      }

      id = R.id.credit_radioBtn;
      RadioButton creditRadioBtn = ViewBindings.findChildViewById(rootView, id);
      if (creditRadioBtn == null) {
        break missingId;
      }

      id = R.id.discount;
      TextView discount = ViewBindings.findChildViewById(rootView, id);
      if (discount == null) {
        break missingId;
      }

      id = R.id.discount_tv;
      TextView discountTv = ViewBindings.findChildViewById(rootView, id);
      if (discountTv == null) {
        break missingId;
      }

      id = R.id.email_textView;
      TextView emailTextView = ViewBindings.findChildViewById(rootView, id);
      if (emailTextView == null) {
        break missingId;
      }

      id = R.id.first_line;
      View firstLine = ViewBindings.findChildViewById(rootView, id);
      if (firstLine == null) {
        break missingId;
      }

      id = R.id.fourth_line;
      View fourthLine = ViewBindings.findChildViewById(rootView, id);
      if (fourthLine == null) {
        break missingId;
      }

      id = R.id.line;
      View line = ViewBindings.findChildViewById(rootView, id);
      if (line == null) {
        break missingId;
      }

      id = R.id.name_textView;
      TextView nameTextView = ViewBindings.findChildViewById(rootView, id);
      if (nameTextView == null) {
        break missingId;
      }

      id = R.id.order_btn;
      Button orderBtn = ViewBindings.findChildViewById(rootView, id);
      if (orderBtn == null) {
        break missingId;
      }

      id = R.id.payment_btn;
      CircleImageView paymentBtn = ViewBindings.findChildViewById(rootView, id);
      if (paymentBtn == null) {
        break missingId;
      }

      id = R.id.payment_method_textview;
      TextView paymentMethodTextview = ViewBindings.findChildViewById(rootView, id);
      if (paymentMethodTextview == null) {
        break missingId;
      }

      id = R.id.payment_rg;
      RadioGroup paymentRg = ViewBindings.findChildViewById(rootView, id);
      if (paymentRg == null) {
        break missingId;
      }

      id = R.id.phone_textView;
      TextView phoneTextView = ViewBindings.findChildViewById(rootView, id);
      if (phoneTextView == null) {
        break missingId;
      }

      id = R.id.progressbar;
      ProgressBar progressbar = ViewBindings.findChildViewById(rootView, id);
      if (progressbar == null) {
        break missingId;
      }

      id = R.id.shipping;
      TextView shipping = ViewBindings.findChildViewById(rootView, id);
      if (shipping == null) {
        break missingId;
      }

      id = R.id.shipping_fee_tv;
      TextView shippingFeeTv = ViewBindings.findChildViewById(rootView, id);
      if (shippingFeeTv == null) {
        break missingId;
      }

      id = R.id.shipping_txt;
      TextView shippingTxt = ViewBindings.findChildViewById(rootView, id);
      if (shippingTxt == null) {
        break missingId;
      }

      id = R.id.shipping_txt_input;
      TextView shippingTxtInput = ViewBindings.findChildViewById(rootView, id);
      if (shippingTxtInput == null) {
        break missingId;
      }

      id = R.id.subtotal;
      TextView subtotal = ViewBindings.findChildViewById(rootView, id);
      if (subtotal == null) {
        break missingId;
      }

      id = R.id.subtotal_tv;
      TextView subtotalTv = ViewBindings.findChildViewById(rootView, id);
      if (subtotalTv == null) {
        break missingId;
      }

      id = R.id.third_line;
      View thirdLine = ViewBindings.findChildViewById(rootView, id);
      if (thirdLine == null) {
        break missingId;
      }

      id = R.id.top_view;
      View topView = ViewBindings.findChildViewById(rootView, id);
      if (topView == null) {
        break missingId;
      }

      id = R.id.total_price_label;
      TextView totalPriceLabel = ViewBindings.findChildViewById(rootView, id);
      if (totalPriceLabel == null) {
        break missingId;
      }

      id = R.id.total_price_textview;
      TextView totalPriceTextview = ViewBindings.findChildViewById(rootView, id);
      if (totalPriceTextview == null) {
        break missingId;
      }

      id = R.id.verify;
      ImageView verify = ViewBindings.findChildViewById(rootView, id);
      if (verify == null) {
        break missingId;
      }

      return new FragmentOrderConfirmationBinding((ConstraintLayout) rootView, addressBtn,
          addressTitle, backBtn, bodyLayout, bottomLayout, bottomView, cashRadioBtn, couponEditText,
          couponTextview, creditRadioBtn, discount, discountTv, emailTextView, firstLine,
          fourthLine, line, nameTextView, orderBtn, paymentBtn, paymentMethodTextview, paymentRg,
          phoneTextView, progressbar, shipping, shippingFeeTv, shippingTxt, shippingTxtInput,
          subtotal, subtotalTv, thirdLine, topView, totalPriceLabel, totalPriceTextview, verify);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
