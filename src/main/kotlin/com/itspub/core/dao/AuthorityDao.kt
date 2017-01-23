package com.itspub.core.dao

import com.itspub.core.vo.Authority

/**
 * Created by Administrator on 2017/1/20.
 */
interface AuthorityDao {

    fun listAll(): List<Authority>
}