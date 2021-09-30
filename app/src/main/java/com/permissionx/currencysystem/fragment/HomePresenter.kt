package com.permissionx.currencysystem.fragment

import com.hankkin.library.mvp.presenter.RxLifePresenter
import com.hankkin.library.utils.LogUtils
import com.permissionx.currencysystem.http.HttpClientUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter :RxLifePresenter<HomeContract.IView>(),HomeContract.IPresenter{

    override fun invitationList() {
        getMvpView().showLoading()
        HttpClientUtils.Builder.getCommonHttp()
            .invitation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeTx ({
                getMvpView().hideLoading()
                getMvpView().invitationListResult(it.list)
            },{
                LogUtils.d(it.message)
                getMvpView().showErrorMsg(it.message!!)
                getMvpView().hideLoading()
            }).bindRxLifeEx(RxLife.ON_DESTROY)
    }

}