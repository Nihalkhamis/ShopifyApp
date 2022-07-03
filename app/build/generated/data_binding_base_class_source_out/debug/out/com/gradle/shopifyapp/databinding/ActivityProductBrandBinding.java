// Generated by view binder compiler. Do not edit!
package com.gradle.shopifyapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.gradle.shopifyapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityProductBrandBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView backBtn;

  @NonNull
  public final ConstraintLayout backSearchLayout;

  @NonNull
  public final ImageView cartImg;

  @NonNull
  public final ImageView favImg;

  @NonNull
  public final RecyclerView productBrandRV;

  @NonNull
  public final ProgressBar progressbar;

  @NonNull
  public final ImageView searchBtn;

  @NonNull
  public final EditText searchEt;

  @NonNull
  public final ConstraintLayout subcategoryLayout;

  @NonNull
  public final TextView subcategoryNameTxt;

  private ActivityProductBrandBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView backBtn, @NonNull ConstraintLayout backSearchLayout,
      @NonNull ImageView cartImg, @NonNull ImageView favImg, @NonNull RecyclerView productBrandRV,
      @NonNull ProgressBar progressbar, @NonNull ImageView searchBtn, @NonNull EditText searchEt,
      @NonNull ConstraintLayout subcategoryLayout, @NonNull TextView subcategoryNameTxt) {
    this.rootView = rootView;
    this.backBtn = backBtn;
    this.backSearchLayout = backSearchLayout;
    this.cartImg = cartImg;
    this.favImg = favImg;
    this.productBrandRV = productBrandRV;
    this.progressbar = progressbar;
    this.searchBtn = searchBtn;
    this.searchEt = searchEt;
    this.subcategoryLayout = subcategoryLayout;
    this.subcategoryNameTxt = subcategoryNameTxt;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityProductBrandBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityProductBrandBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_product_brand, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityProductBrandBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.back_btn;
      ImageView backBtn = ViewBindings.findChildViewById(rootView, id);
      if (backBtn == null) {
        break missingId;
      }

      id = R.id.back_search_layout;
      ConstraintLayout backSearchLayout = ViewBindings.findChildViewById(rootView, id);
      if (backSearchLayout == null) {
        break missingId;
      }

      id = R.id.cart_img;
      ImageView cartImg = ViewBindings.findChildViewById(rootView, id);
      if (cartImg == null) {
        break missingId;
      }

      id = R.id.fav_img;
      ImageView favImg = ViewBindings.findChildViewById(rootView, id);
      if (favImg == null) {
        break missingId;
      }

      id = R.id.productBrandRV;
      RecyclerView productBrandRV = ViewBindings.findChildViewById(rootView, id);
      if (productBrandRV == null) {
        break missingId;
      }

      id = R.id.progressbar;
      ProgressBar progressbar = ViewBindings.findChildViewById(rootView, id);
      if (progressbar == null) {
        break missingId;
      }

      id = R.id.search_btn;
      ImageView searchBtn = ViewBindings.findChildViewById(rootView, id);
      if (searchBtn == null) {
        break missingId;
      }

      id = R.id.search_et;
      EditText searchEt = ViewBindings.findChildViewById(rootView, id);
      if (searchEt == null) {
        break missingId;
      }

      id = R.id.subcategory_layout;
      ConstraintLayout subcategoryLayout = ViewBindings.findChildViewById(rootView, id);
      if (subcategoryLayout == null) {
        break missingId;
      }

      id = R.id.subcategoryName_txt;
      TextView subcategoryNameTxt = ViewBindings.findChildViewById(rootView, id);
      if (subcategoryNameTxt == null) {
        break missingId;
      }

      return new ActivityProductBrandBinding((ConstraintLayout) rootView, backBtn, backSearchLayout,
          cartImg, favImg, productBrandRV, progressbar, searchBtn, searchEt, subcategoryLayout,
          subcategoryNameTxt);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}