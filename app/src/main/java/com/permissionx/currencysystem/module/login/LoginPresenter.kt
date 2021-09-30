package com.permissionx.currencysystem.module.login

import com.hankkin.library.mvp.presenter.RxLifePresenter
import com.hankkin.library.utils.LogUtils
import com.permissionx.currencysystem.http.HttpClientUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginPresenter : RxLifePresenter<LoginContract.IView>(), LoginContract.IPresenter {

    override fun loginPwd(mobile: String, password: String) {
        LogUtils.e("SPUtils",mobile+password)
        getMvpView().showLoading()
        HttpClientUtils.Builder.getCommonHttp()
            .login(mobile,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeTx({
                getMvpView().hideLoading()
                getMvpView().loginResult(it)
            },{
                LogUtils.d(it.message)
                getMvpView().showErrorMsg(it.message!!)
                getMvpView().hideLoading()
            }).bindRxLifeEx(RxLife.ON_DESTROY)

    }


}