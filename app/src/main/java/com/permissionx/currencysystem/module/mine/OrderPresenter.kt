package com.permissionx.currencysystem.module.mine

import android.util.Log
import com.hankkin.library.mvp.presenter.RxLifePresenter
import com.hankkin.library.utils.LogUtils
import com.permissionx.currencysystem.http.HttpClientUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.math.hypot

class OrderPresenter : RxLifePresenter<OrderContract.IView>(),OrderContract.IPresenter{

    override fun getData(page: String, status: String) {
        var sta=status
        if (status == "all"){
            sta=""
        }
        Log.e("OrderPresenter",sta)
        getMvpView().showLoading()
            HttpClientUtils.Builder.getCommonHttp()
                .getOrderData(page, sta)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeTx(
                    {
                        getMvpView().hideLoading()
                        getMvpView().getDataResult(it)
                    },
                    {
                         LogUtils.d(it.message)
                        getMvpView().showErrorMsg(it.message!!)
                        getMvpView().hideLoading()
                    }
                ).bindRxLifeEx(RxLife.ON_DESTROY)
    }
}