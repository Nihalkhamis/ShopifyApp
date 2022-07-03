// Generated by view binder compiler. Do not edit!
package com.gradle.shopifyapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.gradle.shopifyapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySearchBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton cancel;

  @NonNull
  public final EditText searchEditText;

  @NonNull
  public final LinearLayout searchLayout;

  @NonNull
  public final RecyclerView searchList;

  private ActivitySearchBinding(@NonNull ConstraintLayout rootView, @NonNull AppCompatButton cancel,
      @NonNull EditText searchEditText, @NonNull LinearLayout searchLayout,
      @NonNull RecyclerView searchList) {
    this.rootView = rootView;
    this.cancel = cancel;
    this.searchEditText = searchEditText;
    this.searchLayout = searchLayout;
    this.searchList = searchList;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySearchBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySearchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_search, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySearchBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cancel;
      AppCompatButton cancel = ViewBindings.findChildViewById(rootView, id);
      if (cancel == null) {
        break missingId;
      }

      id = R.id.search_edit_text;
      EditText searchEditText = ViewBindings.findChildViewById(rootView, id);
      if (searchEditText == null) {
        break missingId;
      }

      id = R.id.search_layout;
      LinearLayout searchLayout = ViewBindings.findChildViewById(rootView, id);
      if (searchLayout == null) {
        break missingId;
      }

      id = R.id.search_list;
      RecyclerView searchList = ViewBindings.findChildViewById(rootView, id);
      if (searchList == null) {
        break missingId;
      }

      return new ActivitySearchBinding((ConstraintLayout) rootView, cancel, searchEditText,
          searchLayout, searchList);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}