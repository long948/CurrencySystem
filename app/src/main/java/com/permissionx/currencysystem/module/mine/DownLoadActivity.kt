package com.permissionx.currencysystem.module.mine

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.permissionx.currencysystem.R
import com.permissionx.currencysystem.utils.ClipboardUtils

import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.activity_down_load.*
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class DownLoadActivity : AppCompatActivity() {
    private var mBitmap: Bitmap? = null
    fun saveBitmap(filePath: String, fileName: String, b: Bitmap) {
        val file = File("$filePath/$fileName")
        try {
            val fout = FileOutputStream(file)
            val bos = BufferedOutputStream(fout)
            b.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            bos.flush()
            bos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // 上面的先转file再保存，不知道为啥，本来可以直接调用保存Bitmap的方法，上面方法在华为Mate30的Android Q崩溃
        MediaStore.Images.Media.insertImage(contentResolver, b, fileName, null)
        sendBroadcast(
            Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse("file://" + file.absolutePath)
            )
        )
//        ToastUtils.showShort("保存成功")
        Toast.makeText(this,"保存成功！",Toast.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_down_load)
        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
        user_info_topBar.setTitle("下载链接")
        user_info_topBar.addLeftBackImageButton().setOnClickListener { finish() }
        tv_copy_address.setOnClickListener {
            ClipboardUtils.copyText("http://39.107.93.58/api/download_app")
            ToastUtils.showShort("复制成功")

        }
        tv_save.setOnClickListener {
            if (mBitmap == null) {
                return@setOnClickListener
            }
            val filepath =
                Environment.getExternalStoragePublicDirectory("DCIM").absolutePath + File.separator + ""
            val fileName = System.currentTimeMillis().toString() + "jpg"
            saveBitmap(filepath, fileName, mBitmap!!)
        }
//        Glide.with(this).load(R.mipmap.download).into(iv_icon)
        Glide.with(this).asBitmap()
            .load(R.mipmap.download)
            .addListener(object :RequestListener<Bitmap>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                  mBitmap=resource
                    return false
                }
            }).into(iv_icon)
//        Glide.with(this)
//            .asBitmap()
//            .load(R.mipmap.download)
//            .addListener(object : RequestListener<Bitmap> {
//                override fun onLoadFailed(
//                    @Nullable e: GlideException?, model: Any,
//                    target: Target<Bitmap>,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    return false
//                }
//
//                override fun onResourceReady(
//                    resource: Bitmap,
//                    model: Any,
//                    target: Target<Bitmap>,
//                    dataSource: DataSource,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    mBitmap = resource
//                    return false
//                }
//            }).into(iv_icon)
    }
}
