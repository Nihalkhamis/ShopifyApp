// Generated by view binder compiler. Do not edit!
package com.gradle.shopifyapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.tabs.TabLayout;
import com.gradle.shopifyapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCategoryBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TabLayout categoryTabLayout;

  @NonNull
  public final RecyclerView productTypeRV;

  @NonNull
  public final ProgressBar progressbar;

  @NonNull
  public final RecyclerView subCategoryRV;

  private FragmentCategoryBinding(@NonNull ConstraintLayout rootView,
      @NonNull TabLayout categoryTabLayout, @NonNull RecyclerView productTypeRV,
      @NonNull ProgressBar progressbar, @NonNull RecyclerView subCategoryRV) {
    this.rootView = rootView;
    this.categoryTabLayout = categoryTabLayout;
    this.productTypeRV = productTypeRV;
    this.progressbar = progressbar;
    this.subCategoryRV = subCategoryRV;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCategoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCategoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_category, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCategoryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.categoryTabLayout;
      TabLayout categoryTabLayout = ViewBindings.findChildViewById(rootView, id);
      if (categoryTabLayout == null) {
        break missingId;
      }

      id = R.id.productTypeRV;
      RecyclerView productTypeRV = ViewBindings.findChildViewById(rootView, id);
      if (productTypeRV == null) {
        break missingId;
      }

      id = R.id.progressbar;
      ProgressBar progressbar = ViewBindings.findChildViewById(rootView, id);
      if (progressbar == null) {
        break missingId;
      }

      id = R.id.subCategoryRV;
      RecyclerView subCategoryRV = ViewBindings.findChildViewById(rootView, id);
      if (subCategoryRV == null) {
        break missingId;
      }

      return new FragmentCategoryBinding((ConstraintLayout) rootView, categoryTabLayout,
          productTypeRV, progressbar, subCategoryRV);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}