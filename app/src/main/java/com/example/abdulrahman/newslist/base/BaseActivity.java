
package com.example.abdulrahman.newslist.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdulrahman.newslist.NewsListApp;
import com.example.abdulrahman.newslist.R;
import com.example.abdulrahman.newslist.data.Local.prefs.PrefrencesHelperImp;
import com.example.abdulrahman.newslist.di.component.ActivityComponent;
import com.example.abdulrahman.newslist.di.component.DaggerActivityComponent;
import com.example.abdulrahman.newslist.di.module.ActivityModule;
import com.example.abdulrahman.newslist.utils.CommonUtils;
import com.example.abdulrahman.newslist.utils.NetworkUtils;

import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity implements View, BaseFragment.Callback {

    private ProgressDialog mProgressDialog;
    PrefrencesHelperImp prefrencesHelperImp;

    private ActivityComponent mActivityComponent;

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((NewsListApp) getApplication()).getComponent())
                .build();
        prefrencesHelperImp=PrefrencesHelperImp.getInstance(getApplicationContext());
//        getSupportActionBar().hide();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_LONG);
        android.view.View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }



    protected void showSnackBarNetwork(int message , int color ,int time){

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, time);
        android.view.View sbView = snackbar.getView();

        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        sbView.setBackgroundColor(color);
        snackbar.show();

    }




    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.some_error));
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        showMessage(getString(resId));
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void onFragmentAttached() {

    }


    @Override
    public void onFragmentDetached(String tag) {

    }

    public void hideKeyboard() {
        android.view.View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



    @Override
    public void openActivityOnTokenExpire() {
        /*startActivity(LoginActivity.getStartIntent(this));
        finish();*/
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }



    @Override
    public void networkUnAvailable(){
        showSnackBarNetwork(R.string.network_unavailable , Color.RED , Snackbar.LENGTH_INDEFINITE);

    }



    @Override
    public void networkAvailable(){
        showSnackBarNetwork(R.string.network_available, Color.GREEN , Snackbar.LENGTH_SHORT);

    }





}
