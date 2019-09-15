package com.baibeiyun.bbyiot.module.home.ui.devicevideo;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.common.IConstant;
import com.baibeiyun.bbyiot.model.Response.DeviceVideoInfoResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseFragment;
import com.baibeiyun.bbyiot.module.home.contract.devicevideo.VideoInfoContract;
import com.baibeiyun.bbyiot.module.home.presenter.devicevideo.VideoInfoPresenter;
import com.ezvizuikit.open.EZUIError;
import com.ezvizuikit.open.EZUIKit;
import com.ezvizuikit.open.EZUIPlayer;

import java.util.Calendar;

import butterknife.BindView;

public class VideoDeviceFragment extends BaseFragment<VideoInfoPresenter> implements VideoInfoContract.View {


    @BindView(R.id.player_ui)
    EZUIPlayer playerUi;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frg_video_device;
    }

    @Override
    protected VideoInfoPresenter getPresenter() {
        return new VideoInfoPresenter(getActivity());
    }

    @Override
    public void getDeviceVideoFinish(DeviceVideoInfoResponse response) {

    }

    @Override
    public void getAssentToken(String token) {
        initView(token);
    }


    @Override
    protected void initViewsAndEvents() {
        mPresenter.getAccentToken();
//        initView();
    }


    /**
     * 初始化控件
     */
    private void initView(String token) {
        //初始化EZUIKit
        EZUIKit.initWithAppKey(getActivity().getApplication(), IConstant.appKey);
        //设置授权token
        EZUIKit.setAccessToken(token);
        //设置播放回调callback
        playerUi.setCallBack(new EZUIPlayer.EZUIPlayerCallBack() {
            @Override
            public void onPlaySuccess() {

            }

            @Override
            public void onPlayFail(EZUIError ezuiError) {
                Log.e("hhhhh", ezuiError.getErrorString() + "&&&&&" + ezuiError.getInternalErrorCode());
            }

            @Override
            public void onVideoSizeChange(int i, int i1) {

            }

            @Override
            public void onPrepared() {
                playerUi.startPlay();
            }

            @Override
            public void onPlayTime(Calendar calendar) {

            }

            @Override
            public void onPlayFinish() {

            }
        });
        Log.e("hhhhh", token + "    " + DeviceVideoDetailsActivity.mDeviceUrl);

        //设置加载需要显示的view
        playerUi.setLoadingView(initProgressBar());
        //设置播放参数
        playerUi.setUrl(DeviceVideoDetailsActivity.mDeviceUrl);
        //开始播放
//        playerUi.startPlay();
    }


    /**
     * 创建加载view
     *
     * @return
     */
    private View initProgressBar() {
        RelativeLayout relativeLayout = new RelativeLayout(getActivity());
        relativeLayout.setBackgroundColor(Color.parseColor("#000000"));
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        relativeLayout.setLayoutParams(lp);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.CENTER_IN_PARENT);//addRule参数对应RelativeLayout XML布局的属性
        ProgressBar mProgressBar = new ProgressBar(getActivity());
        mProgressBar.setIndeterminateDrawable(getResources().getDrawable(R.drawable.progress));
        relativeLayout.addView(mProgressBar, rlp);
        return relativeLayout;
    }


    @Override
    public void onStop() {
        super.onStop();

        //停止播放
        playerUi.stopPlay();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        playerUi.releasePlayer();
    }

}
