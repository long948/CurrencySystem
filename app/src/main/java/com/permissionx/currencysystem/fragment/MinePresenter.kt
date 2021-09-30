package com.permissionx.currencysystem.fragment

import com.hankkin.library.mvp.presenter.RxLifePresenter
import com.hankkin.library.utils.LogUtils
import com.permissionx.currencysystem.http.HttpClientUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MinePresenter :RxLifePresenter<MineContract.IView>(),MineContract.IPresenter{

    override fun getUserData() {
        getMvpView().showLoading()
        HttpClientUtils.Builder.getCommonHttp()
            .userData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeTx({
                getMvpView().hideLoading()
                getMvpView().getUserDataResult(it)
            },{
                LogUtils.d(it.message)
                getMvpView().showErrorMsg(it.message!!)
                getMvpView().hideLoading()
            }).bindRxLifeEx(RxLife.ON_DESTROY)
    }

}