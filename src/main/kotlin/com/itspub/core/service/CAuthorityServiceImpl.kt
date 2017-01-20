package com.itspub.core.service

import com.itspub.core.dao.CAuthorityDao
import com.itspub.core.vo.CAuthority
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

/**
 * Created by Administrator on 2017/1/20.
 */
@Transactional(readOnly = true, propagation = Propagation.NEVER)
@Service
open class CAuthorityServiceImpl @Autowired constructor(@Autowired var cAuthorityDao: CAuthorityDao) : CAuthorityService {



    override fun listAll(): List<CAuthority> {
        return cAuthorityDao!!.listAll()
    }
}