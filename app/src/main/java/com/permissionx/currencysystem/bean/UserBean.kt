package com.permissionx.currencysystem.bean

import java.io.Serializable

data class UserBean(
    var id: String? = null,
    var invite_id: String? = null,
    var parent_id: String? = null,
    var team_id: String? = null,
    var mobile: String? = null,
    var name: String? = null,
    var level: String? = null,
    var avatar: String? = null,
    var power: String? = null,
    var bzz_power: String? = null,
    var fil_power: String? = null,
    var wallet_addr: String? = null,
    var wallet_addr_qrcode: String? = null,
    var invite_unnode_num: Int = 0,
    var invite_code: String? = null,
    var team_power: String? = null,
    var usdt: String? = null,
    var total_usdt: String? = null,
    var status: Int = 0,
    var status_txt: String? = null,
    var qrcode_url: String? = null,
    var share_url: String? = null,


    var bankcard_no: String? = null,
    var bank_name: String? = null,
    var bankcard_name: String? = null,
    var alipay: String? = null,
    var wallet: String? = null,
    var wallet_usdt: String? = null,
    var default_account: String? = null,
    var reward_power: String? = null,
    var is_id_card_auth: String? = null,

    var fil: String? = null,
    var xch: String? = null

):Serializable