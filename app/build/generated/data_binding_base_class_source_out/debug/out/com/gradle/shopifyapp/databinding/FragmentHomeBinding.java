// Generated by view binder compiler. Do not edit!
package com.gradle.shopifyapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.afollestad.viewpagerdots.DotsIndicator;
import com.gradle.shopifyapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final RecyclerView brandRowRv;

  @NonNull
  public final TextView brandsTxt;

  @NonNull
  public final RecyclerView couponsRowRv;

  @NonNull
  public final DotsIndicator dots;

  @NonNull
  public final NestedScrollView nestedScrollView;

  @NonNull
  public final ViewPager pageView;

  @NonNull
  public final ProgressBar progressbar;

  private FragmentHomeBinding(@NonNull NestedScrollView rootView, @NonNull RecyclerView brandRowRv,
      @NonNull TextView brandsTxt, @NonNull RecyclerView couponsRowRv, @NonNull DotsIndicator dots,
      @NonNull NestedScrollView nestedScrollView, @NonNull ViewPager pageView,
      @NonNull ProgressBar progressbar) {
    this.rootView = rootView;
    this.brandRowRv = brandRowRv;
    this.brandsTxt = brandsTxt;
    this.couponsRowRv = couponsRowRv;
    this.dots = dots;
    this.nestedScrollView = nestedScrollView;
    this.pageView = pageView;
    this.progressbar = progressbar;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.brand_row_rv;
      RecyclerView brandRowRv = ViewBindings.findChildViewById(rootView, id);
      if (brandRowRv == null) {
        break missingId;
      }

      id = R.id.brands_txt;
      TextView brandsTxt = ViewBindings.findChildViewById(rootView, id);
      if (brandsTxt == null) {
        break missingId;
      }

      id = R.id.coupons_row_rv;
      RecyclerView couponsRowRv = ViewBindings.findChildViewById(rootView, id);
      if (couponsRowRv == null) {
        break missingId;
      }

      id = R.id.dots;
      DotsIndicator dots = ViewBindings.findChildViewById(rootView, id);
      if (dots == null) {
        break missingId;
      }

      NestedScrollView nestedScrollView = (NestedScrollView) rootView;

      id = R.id.pageView;
      ViewPager pageView = ViewBindings.findChildViewById(rootView, id);
      if (pageView == null) {
        break missingId;
      }

      id = R.id.progressbar;
      ProgressBar progressbar = ViewBindings.findChildViewById(rootView, id);
      if (progressbar == null) {
        break missingId;
      }

      return new FragmentHomeBinding((NestedScrollView) rootView, brandRowRv, brandsTxt,
          couponsRowRv, dots, nestedScrollView, pageView, progressbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
