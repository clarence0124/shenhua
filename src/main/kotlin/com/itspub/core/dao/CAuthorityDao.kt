package com.itspub.core.dao

import com.itspub.core.vo.CAuthority

/**
 * Created by Administrator on 2017/1/20.
 */
interface CAuthorityDao {

    fun listAll(): List<CAuthority>
}