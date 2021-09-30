package com.permissionx.currencysystem.module.init

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import com.hankkin.library.utils.SPUtils
import com.permissionx.currencysystem.common.Constant
import com.permissionx.currencysystem.main.MainActivity
import com.permissionx.currencysystem.module.login.LoginPwdActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            var user_token=SPUtils.getString(Constant.SP_KEY.USER_TOKEN)
            if (TextUtils.isEmpty(user_token)){
            startActivity(Intent(this,LoginPwdActivity::class.java))}

            else{
                startActivity(Intent(this,MainActivity::class.java))
            }
        },2000)
    }
}
