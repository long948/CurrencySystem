package com.permissionx.currencysystem.base

import android.os.Bundle
import com.hankkin.library.mvp.contract.IPresenterContract
import com.hankkin.library.mvp.view.MvpActivity
import com.qmuiteam.qmui.util.QMUIStatusBarHelper

abstract class BaseMvpActivity<out P :IPresenterContract>:MvpActivity<P>(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
        initView()
    }
    abstract fun getLayoutId(): Int
    open fun initView() {}

}