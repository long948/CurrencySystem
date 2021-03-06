package com.hankkin.library.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import androidx.annotation.NonNull;
import android.view.Window;

import com.hankkin.library.R;
import com.hankkin.library.widget.view.indicators.AVLoadingIndicatorView;


/**
 * Created by hankkin on 2017/9/30.
 * Blog: http://hankkin.cn
 * Mail: 1019283569@qq.com
 */

public class HLoading extends Dialog {


    private Context context;
    AVLoadingIndicatorView avi;
    private int color;

    public HLoading(@NonNull Context context,int color) {
        super(context, R.style.progress);
        this.context = context;
        this.color = color;
        init();
    }

    private void init(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.hd_custom_loading_view);
        getWindow().setBackgroundDrawableResource(R.color.base_transparent_buff);
    }


    @Override
    public void onBackPressed() {
        dismiss();
    }
}
