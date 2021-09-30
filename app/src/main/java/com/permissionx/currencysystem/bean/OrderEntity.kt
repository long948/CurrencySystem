package com.permissionx.currencysystem.bean

import java.io.Serializable
/**
 * wait
 * waitconfirm
 * runing
 * complete
 * id : 93
 * product_id : 27
 * price : 12.00
 * num : 2
 * power : 24
 * total : 24
 * product_name : 昵称
 * product_price : 12.00
 * pay_screenshot : null
 * start :
 * end :
 * status : 0
 * status_txt : 待支付
 * <p>
 * case 0: return '待支付'; break;
 * case 1: return '审核中'; break;
 * case 2: return '进行中'; break;
 * case 3: return '已完成'; break;
 */
data class OrderEntity(
//    val STATUS_NOPAY:Int? = 0,
//    val STATUS_CHECK :Int= 1,
    var id: String? = null,
    var product_id: String? = null,
    var product_cover: String? = null,
    var price: String? = null,
    var num: String? = null,
    var price_unit: String? = null,
    var power: String? = null,
    var total: String? = null,
    var product_name: String? = null,
    var product_price: String? = null,
    var pay_screenshot: String? = null,
    var start: String? = null,
    var end: String? = null,
    var status: Int = 0,
    var status_txt: String? = null,
    var duration: String? = null,
    var wallet_address: String? = null,
    var wallet_qrcode: String? = null,
    var wechat_client: String? = null,
    var use_usdt: String? = null,
    var need_pay: String? = null,
    var target: String? = null,
    var amount: String? = null,
    var fil_addr: String? = null,
    var created_at: String? = null,
    var fil_product_id: String? = null,
    var income_fil: String? = null,
    var open_days: String? = null,
    var member_id: String? = null,
    var product_type: String? = null
) :Serializable