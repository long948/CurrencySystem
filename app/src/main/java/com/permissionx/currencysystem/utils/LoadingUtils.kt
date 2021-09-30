package com.permissionx.currencysystem.utils
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import com.hankkin.library.widget.dialog.HLoading

object LoadingUtils {

    @SuppressLint("StaticFieldLeak")
    private var loading: HLoading? = null

    fun showLoading(context: Context?) {
        if (loading == null) {
            loading = HLoading(context!!, Color.parseColor("#ff0000"))
        }
        loading!!.show()
    }

    fun hideLoading() {
        if (loading != null) {
            loading!!.dismiss()
        }
        onDestory()
    }

    fun onDestory() {
        if (loading != null) {
            loading = null
        }
    }
}