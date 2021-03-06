// Generated by view binder compiler. Do not edit!
package com.gradle.shopifyapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gradle.shopifyapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSettingsAddressBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final FloatingActionButton addAddressFloatBtn;

  @NonNull
  public final RecyclerView addressesRV;

  @NonNull
  public final ImageView backBtn;

  @NonNull
  public final TextView currencyTxt;

  @NonNull
  public final ProgressBar progressbar;

  private FragmentSettingsAddressBinding(@NonNull ConstraintLayout rootView,
      @NonNull FloatingActionButton addAddressFloatBtn, @NonNull RecyclerView addressesRV,
      @NonNull ImageView backBtn, @NonNull TextView currencyTxt, @NonNull ProgressBar progressbar) {
    this.rootView = rootView;
    this.addAddressFloatBtn = addAddressFloatBtn;
    this.addressesRV = addressesRV;
    this.backBtn = backBtn;
    this.currencyTxt = currencyTxt;
    this.progressbar = progressbar;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSettingsAddressBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSettingsAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_settings_address, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSettingsAddressBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addAddress_floatBtn;
      FloatingActionButton addAddressFloatBtn = ViewBindings.findChildViewById(rootView, id);
      if (addAddressFloatBtn == null) {
        break missingId;
      }

      id = R.id.addressesRV;
      RecyclerView addressesRV = ViewBindings.findChildViewById(rootView, id);
      if (addressesRV == null) {
        break missingId;
      }

      id = R.id.back_btn;
      ImageView backBtn = ViewBindings.findChildViewById(rootView, id);
      if (backBtn == null) {
        break missingId;
      }

      id = R.id.currency_txt;
      TextView currencyTxt = ViewBindings.findChildViewById(rootView, id);
      if (currencyTxt == null) {
        break missingId;
      }

      id = R.id.progressbar;
      ProgressBar progressbar = ViewBindings.findChildViewById(rootView, id);
      if (progressbar == null) {
        break missingId;
      }

      return new FragmentSettingsAddressBinding((ConstraintLayout) rootView, addAddressFloatBtn,
          addressesRV, backBtn, currencyTxt, progressbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
