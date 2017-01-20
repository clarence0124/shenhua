package com.itspub.core.service

import com.itspub.core.vo.CAuthority

/**
 * Created by Administrator on 2017/1/20.
 */
interface CAuthorityService {
    fun listAll(): List<CAuthority>
}