package com.baibeiyun.bbyiot.module;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.DataManager;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.login.ui.LoginActivity;
import com.baibeiyun.bbyiot.module.main.ui.MainActivity;
import com.baibeiyun.bbyiot.utils.ActivityUtils;

public class SplashActivity extends BaseActivity {
    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_splash;
    }

    @Override
    protected void initViewsAndEvents() {

        //startActivity(new Intent(this, DemoActivity.class));
        if (DataManager.getsInstance().isLogin()) {
            ActivityUtils.startActivity(this, MainActivity.class);
        } else {
            ActivityUtils.startActivity(this, LoginActivity.class);
        }
        requestPermission();
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }


    private void requestPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    }, 1);

        }
    }

}
