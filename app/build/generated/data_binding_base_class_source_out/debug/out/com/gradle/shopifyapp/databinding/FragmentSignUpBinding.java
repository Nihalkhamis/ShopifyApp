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
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.gradle.shopifyapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSignUpBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView cancelIc;

  @NonNull
  public final EditText confirmPasswordEdt;

  @NonNull
  public final ImageView confirmPasswordIc;

  @NonNull
  public final EditText emailEdt;

  @NonNull
  public final ImageView emailIc;

  @NonNull
  public final EditText firstNameEdt;

  @NonNull
  public final EditText passwordEdt;

  @NonNull
  public final ImageView passwordIc;

  @NonNull
  public final ImageView personIc;

  @NonNull
  public final EditText phoneEdt;

  @NonNull
  public final ImageView phoneIc;

  @NonNull
  public final ProgressBar progressbar;

  @NonNull
  public final EditText secondNameEdt;

  @NonNull
  public final AppCompatButton signupBtn;

  @NonNull
  public final TextView signupTxt;

  @NonNull
  public final ConstraintLayout whiteBox;

  private FragmentSignUpBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView cancelIc,
      @NonNull EditText confirmPasswordEdt, @NonNull ImageView confirmPasswordIc,
      @NonNull EditText emailEdt, @NonNull ImageView emailIc, @NonNull EditText firstNameEdt,
      @NonNull EditText passwordEdt, @NonNull ImageView passwordIc, @NonNull ImageView personIc,
      @NonNull EditText phoneEdt, @NonNull ImageView phoneIc, @NonNull ProgressBar progressbar,
      @NonNull EditText secondNameEdt, @NonNull AppCompatButton signupBtn,
      @NonNull TextView signupTxt, @NonNull ConstraintLayout whiteBox) {
    this.rootView = rootView;
    this.cancelIc = cancelIc;
    this.confirmPasswordEdt = confirmPasswordEdt;
    this.confirmPasswordIc = confirmPasswordIc;
    this.emailEdt = emailEdt;
    this.emailIc = emailIc;
    this.firstNameEdt = firstNameEdt;
    this.passwordEdt = passwordEdt;
    this.passwordIc = passwordIc;
    this.personIc = personIc;
    this.phoneEdt = phoneEdt;
    this.phoneIc = phoneIc;
    this.progressbar = progressbar;
    this.secondNameEdt = secondNameEdt;
    this.signupBtn = signupBtn;
    this.signupTxt = signupTxt;
    this.whiteBox = whiteBox;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSignUpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSignUpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_sign_up, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSignUpBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cancel_ic;
      ImageView cancelIc = ViewBindings.findChildViewById(rootView, id);
      if (cancelIc == null) {
        break missingId;
      }

      id = R.id.confirm_password_edt;
      EditText confirmPasswordEdt = ViewBindings.findChildViewById(rootView, id);
      if (confirmPasswordEdt == null) {
        break missingId;
      }

      id = R.id.confirm_password_ic;
      ImageView confirmPasswordIc = ViewBindings.findChildViewById(rootView, id);
      if (confirmPasswordIc == null) {
        break missingId;
      }

      id = R.id.email_edt;
      EditText emailEdt = ViewBindings.findChildViewById(rootView, id);
      if (emailEdt == null) {
        break missingId;
      }

      id = R.id.email_ic;
      ImageView emailIc = ViewBindings.findChildViewById(rootView, id);
      if (emailIc == null) {
        break missingId;
      }

      id = R.id.firstName_edt;
      EditText firstNameEdt = ViewBindings.findChildViewById(rootView, id);
      if (firstNameEdt == null) {
        break missingId;
      }

      id = R.id.password_edt;
      EditText passwordEdt = ViewBindings.findChildViewById(rootView, id);
      if (passwordEdt == null) {
        break missingId;
      }

      id = R.id.password_ic;
      ImageView passwordIc = ViewBindings.findChildViewById(rootView, id);
      if (passwordIc == null) {
        break missingId;
      }

      id = R.id.person_ic;
      ImageView personIc = ViewBindings.findChildViewById(rootView, id);
      if (personIc == null) {
        break missingId;
      }

      id = R.id.phone_edt;
      EditText phoneEdt = ViewBindings.findChildViewById(rootView, id);
      if (phoneEdt == null) {
        break missingId;
      }

      id = R.id.phone_ic;
      ImageView phoneIc = ViewBindings.findChildViewById(rootView, id);
      if (phoneIc == null) {
        break missingId;
      }

      id = R.id.progressbar;
      ProgressBar progressbar = ViewBindings.findChildViewById(rootView, id);
      if (progressbar == null) {
        break missingId;
      }

      id = R.id.secondName_edt;
      EditText secondNameEdt = ViewBindings.findChildViewById(rootView, id);
      if (secondNameEdt == null) {
        break missingId;
      }

      id = R.id.signup_btn;
      AppCompatButton signupBtn = ViewBindings.findChildViewById(rootView, id);
      if (signupBtn == null) {
        break missingId;
      }

      id = R.id.signup_txt;
      TextView signupTxt = ViewBindings.findChildViewById(rootView, id);
      if (signupTxt == null) {
        break missingId;
      }

      id = R.id.white_box;
      ConstraintLayout whiteBox = ViewBindings.findChildViewById(rootView, id);
      if (whiteBox == null) {
        break missingId;
      }

      return new FragmentSignUpBinding((ConstraintLayout) rootView, cancelIc, confirmPasswordEdt,
          confirmPasswordIc, emailEdt, emailIc, firstNameEdt, passwordEdt, passwordIc, personIc,
          phoneEdt, phoneIc, progressbar, secondNameEdt, signupBtn, signupTxt, whiteBox);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
