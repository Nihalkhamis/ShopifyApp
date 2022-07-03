// Generated by view binder compiler. Do not edit!
package com.gradle.shopifyapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.gradle.shopifyapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentStartingBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView appName;

  @NonNull
  public final TextView loginTxt;

  @NonNull
  public final ImageView logoIc;

  @NonNull
  public final Button signupBtn;

  @NonNull
  public final TextView skipTxt;

  private FragmentStartingBinding(@NonNull ConstraintLayout rootView, @NonNull TextView appName,
      @NonNull TextView loginTxt, @NonNull ImageView logoIc, @NonNull Button signupBtn,
      @NonNull TextView skipTxt) {
    this.rootView = rootView;
    this.appName = appName;
    this.loginTxt = loginTxt;
    this.logoIc = logoIc;
    this.signupBtn = signupBtn;
    this.skipTxt = skipTxt;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentStartingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentStartingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_starting, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentStartingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.app_name;
      TextView appName = ViewBindings.findChildViewById(rootView, id);
      if (appName == null) {
        break missingId;
      }

      id = R.id.login_txt;
      TextView loginTxt = ViewBindings.findChildViewById(rootView, id);
      if (loginTxt == null) {
        break missingId;
      }

      id = R.id.logo_ic;
      ImageView logoIc = ViewBindings.findChildViewById(rootView, id);
      if (logoIc == null) {
        break missingId;
      }

      id = R.id.signup_btn;
      Button signupBtn = ViewBindings.findChildViewById(rootView, id);
      if (signupBtn == null) {
        break missingId;
      }

      id = R.id.skip_txt;
      TextView skipTxt = ViewBindings.findChildViewById(rootView, id);
      if (skipTxt == null) {
        break missingId;
      }

      return new FragmentStartingBinding((ConstraintLayout) rootView, appName, loginTxt, logoIc,
          signupBtn, skipTxt);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}