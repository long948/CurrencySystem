package com.permissionx.currencysystem.module.login

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.hankkin.library.http.HttpConfig
import com.hankkin.library.utils.LogUtils
import com.hankkin.library.utils.SPUtils
import com.permissionx.currencysystem.main.MainActivity
import com.permissionx.currencysystem.R
import com.permissionx.currencysystem.base.BaseMvpActivity
import com.permissionx.currencysystem.bean.LoginBean
import com.permissionx.currencysystem.common.Constant
import com.permissionx.currencysystem.utils.LoadingUtils
import com.permissionx.currencysystem.utils.ToastUtils
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.activity_login_pwd.*

class LoginPwdActivity : BaseMvpActivity<LoginContract.IPresenter>(),LoginContract.IView {
    override fun getLayoutId()=R.layout.activity_login_pwd

    override fun registerPresenter()=LoginPresenter::class.java



    override fun loginResult(data: LoginBean) {
        LogUtils.e("loginResult","")
//        SPUtils.init(this, Constant.COMMON.SP_NAME)
        SPUtils.put(Constant.SP_KEY.USER_TOKEN,data.token)
        HttpConfig.setHeaders(mapOf("Authorization" to "Bearer "+data.token) )
//        val rmapNew=HashMap<String,String>()
//        rmapNew.put("key", "Bearer "+SPUtils.getString(Constant.SP_KEY.USER_TOKEN,""))
//        rmapNew.put("url", Constant.MelonUrl.BASE_URL)
//        rmapNew.put("type","pwd")
//        UMCrash.generateCustomLog(Gson().toJson(rmapNew),"login")

        startActivity(Intent(this@LoginPwdActivity, MainActivity::class.java))
        finish()
    }

    override fun initView() {
            if (SPUtils.getString(Constant.SP_KEY.USER_TOKEN).isNotEmpty()){
                startActivity(Intent(this,MainActivity::class.java))
                finish()

            }
             ll_login.setOnClickListener {
            if (TextUtils.isEmpty(edt_phone_number.text.toString().trim())){
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(edt_pwd.text.toString().trim())){
                return@setOnClickListener
            }
            Log.e("SPUtils", edt_phone_number.text.toString()+" "+
                edt_pwd.text.toString().trim())
            getPresenter().loginPwd(
                edt_phone_number.text.toString().trim(),
                edt_pwd.text.toString().trim()
            )
        }

    }
    override fun hideLoading() {
        LoadingUtils.hideLoading()
    }
    override fun showLoading() {
        LoadingUtils.showLoading(this)
    }
    override fun showErrorMsg(msg: String) {
//        ToastUtils.showError(this,msg)
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login_pwd)
//    }
}
