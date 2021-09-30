package com.permissionx.currencysystem.module.mine

import com.hankkin.library.mvp.presenter.RxLifePresenter
import com.hankkin.library.utils.LogUtils
import com.permissionx.currencysystem.http.HttpClientUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UserInfoPresenter:RxLifePresenter<UserInfoContract.IView>(),UserInfoContract.IPresenter{
    override fun updateProfile(name: String, avatar: String) {
        getMvpView().showLoading()
        HttpClientUtils.Builder.getCommonHttp()
            .updateProfile(name,avatar)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeTx({
                getMvpView().hideLoading()
                getMvpView().updateProfileResult()
            },{
                LogUtils.d(it.message)
                getMvpView().showErrorMsg(it.message!!)
                getMvpView().hideLoading()
            }).bindRxLifeEx(RxLife.ON_DESTROY)
    }

    override fun uploadImage(path: String) {
        getMvpView().showLoading()
        val mFile=File(path)
        val requestFile:RequestBody= RequestBody.create(MediaType.parse("image/*"),mFile)
        val bodyFile=MultipartBody.Part.createFormData("file",mFile.name,requestFile)
        val bodyMap:HashMap<String,RequestBody> = HashMap()
        bodyMap["path"] = RequestBody.create(MediaType.parse("text/plain"),"path")
        bodyMap["type"] = RequestBody.create(MediaType.parse("text/plain"),"type")
        HttpClientUtils.Builder.getCommonHttp()
            .uploadImage(bodyMap,bodyFile)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeTx({
                getMvpView().hideLoading()
                it.src?.let { src -> getMvpView().uploadImageResult(src) }
            },{
                LogUtils.d(it.message)
                getMvpView().showErrorMsg(it.message!!)
                getMvpView().hideLoading()
            }).bindRxLifeEx(RxLife.ON_DESTROY)
    }
}