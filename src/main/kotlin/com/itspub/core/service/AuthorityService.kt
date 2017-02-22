package com.itspub.core.service

import com.itspub.core.vo.Authority

/**
 * Created by Administrator on 2017/1/20.
 */
interface AuthorityService {
    fun listAll(): java.util.List<Authority>
}