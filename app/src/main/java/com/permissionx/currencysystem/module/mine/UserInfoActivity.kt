package com.permissionx.currencysystem.module.mine


import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.OnInputConfirmListener

import com.permissionx.currencysystem.R
import com.permissionx.currencysystem.base.BaseMvpActivity
import com.permissionx.currencysystem.bean.UserBean
import com.permissionx.currencysystem.utils.ImageLoaderManager
import com.permissionx.currencysystem.utils.LoadingUtils
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity :BaseMvpActivity<UserInfoContract.IPresenter>(),UserInfoContract.IView{
    private var imageUploadSrc=""
    private var imageLocalSrc = ""
    private var txt=""
    override fun getLayoutId()=R.layout.activity_user_info

    override fun registerPresenter()=UserInfoPresenter::class.java

    override fun initView() {
        user_info_topBar.setTitle("修改信息")
        val user:UserBean=intent.getSerializableExtra("user") as UserBean
        user_info_topBar.addLeftBackImageButton().setOnClickListener {
            setResult(10)
            finish() }
        llyt_member_name.setOnClickListener {
            val popupView = XPopup.Builder(this)
                .asInputConfirm("修改姓名",user.name, "请输入要修改的姓名",
                    OnInputConfirmListener { text ->
                        if (TextUtils.isEmpty(text)) {
                            Toast.makeText(this,"姓名不能为空",Toast.LENGTH_SHORT).show()
                            return@OnInputConfirmListener
                        }
                                txt=text
                            getPresenter().updateProfile(text,"")
                    })

            popupView.show()
        }
        tv_name.text=user.name
        ImageLoaderManager.loadCircleImage(this,user.avatar,iv_image)
        iv_image.setOnClickListener {
            selPic()
        }
    }

    private fun selPic() {
        PictureSelector.create(this)
            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
            .maxSelectNum(1)// 最大图片选择数量 int
            .minSelectNum(0)// 最小选择数量 int
            .imageSpanCount(4)// 每行显示个数 int
            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
            .previewImage(true)// 是否可预览图片 true or false
            .previewVideo(false)// 是否可预览视频 true or false
            .enablePreviewAudio(false) // 是否可播放音频 true or false
            .isCamera(true)// 是否显示拍照按钮 true or false
            .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
            .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
            .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
            .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
            .enableCrop(true)// 是否裁剪 true or false
            .compress(true)// 是否压缩 true or false
            .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示 true or false
            .isGif(false)// 是否显示gif图片 true or false
            .circleDimmedLayer(true)
            .minimumCompressSize(100)// 小于100kb的图片不压缩
            .synOrAsy(true)//同步true或异步false 压缩 默认同步
            .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
            .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
            .isDragFrame(false)// 是否可拖动裁剪框(固定)
            .selectionMedia(null)
            .forResult(PictureConfig.CHOOSE_REQUEST)//结果回调onActivityResult code

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            PictureConfig.CHOOSE_REQUEST->if (resultCode== Activity.RESULT_OK){
                val imagesPath=PictureSelector.obtainMultipleResult(data)[0].path
                imageLocalSrc=imagesPath
                imageUploadSrc=""
                getPresenter().uploadImage(imagesPath)
            }
        }
    }


    override fun updateProfileResult() {
        if (txt.isNotEmpty()){
        tv_name.text=txt}
    }

    override fun uploadImageResult(data: String) {
                imageUploadSrc=data
                ImageLoaderManager.loadCircleImage(this,imageLocalSrc,iv_image)
                getPresenter().updateProfile("",imageUploadSrc)
    }

    override fun showLoading() {
        LoadingUtils.showLoading(this)
    }

    override fun hideLoading() {
        LoadingUtils.hideLoading()
    }

    override fun showErrorMsg(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

}
