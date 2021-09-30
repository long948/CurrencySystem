package com.permissionx.currencysystem.bean

data class LoginBean(
    val role:String,
    val token:String,
    val user :User
)
data class User(val id: String,
                val member_info: MemberInfo,
                val openid: String,
                val role: String,
                val type: String,
                val union_uid: String)

data class MemberInfo(
//    val alipay: Any,
//    val avatar: String,
//    val bank_name: Any,
//    val bankcard_name: Any,
//    val bankcard_no: Any,
//    val cash_pass: Any,
//    val created_at: String,
//    val default_account: Int,
//    val deleted_at: Any,
//    val fil: String,
    val id: Int
//    val id_card: Any,
//    val id_card_name: Any,
//    val invite_code: String,
//    val invite_id: Int,
//    val level_id: Int,
//    val mobile: String,
//    val name: String,
//    val node_id: Int,
//    val path: String,
//    val power: String,
//    val reward_power: String,
//    val status: Int,
//    val status_txt: String,
//    val subscribe: Any,
//    val team_id: Any,
//    val team_power: String,
//    val total_usdt: String,
//    val updated_at: String,
//    val usdt: String,
//    val wallet: Any,
//    val wallet_usdt: String
)