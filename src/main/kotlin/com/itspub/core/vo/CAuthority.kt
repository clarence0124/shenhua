package com.itspub.core.vo

import java.io.Serializable

/**
 * Created by Administrator on 2017/1/20.
 */
class CAuthority : Serializable {

    var id: String = ""
    var name: String = ""
    var code: String = ""

    var pid: String? = null
    var hierarchy: Int = 0

    var category: CAuthorityCategory? = null
    var url: String = ""
    var sort: Int = 0
    var remark: String? = null
    var enabled: Boolean = false
    var published: Boolean = false
    var icon: String? = null
}