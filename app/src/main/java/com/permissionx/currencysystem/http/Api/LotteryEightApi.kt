package com.permissionx.currencysystem.http.Api



import com.hankkin.library.domin.BaseResponseMelon
import com.permissionx.currencysystem.bean.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface LotteryEightApi {

    /**
     * 登录
     */
    @POST("/api/login")
    fun login(
        @Query("mobile") mobile:String,
        @Query("password") password:String,
        @Query("type") type:String?="username",
        @Query("role") role:String?="member"
    ): Observable<BaseResponseMelon<LoginBean>>

    /**
     *邀请列表
     */
    @POST("/api/node/list")
        fun invitation(
        @Query("page") page:String?="1"
    ):Observable<BaseResponseMelon<InvitationBean>>

    /**
     * 获取用户信息
     */
        @POST("/api/profile")
        fun userData():Observable<BaseResponseMelon<UserBean>>

    /**
     * 上传图片
     */
    @Multipart
    @POST("/api/upload")
    fun uploadImage(
        @PartMap params:HashMap<String,RequestBody>,
        @Part file:MultipartBody.Part
    ):Observable<BaseResponseMelon<UploadImageBean>>
    /**
     * 更新用户信息
     */
    @POST("/api/update_profile")
    fun updateProfile(
        @Query("name") name :String?,
        @Query("avatar") avatar:String?
    ):Observable<BaseResponseMelon<Any>>

    /**
     *获取用户订单
     */
    @POST("/api/order/list")
    fun getOrderData(
        @Query("page") page: String?,
        @Query("status") status:String
    ):Observable<BaseResponseMelon<List<OrderEntity>>>

}