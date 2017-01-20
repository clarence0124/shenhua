package com.itspub.core.dao

import com.itspub.core.vo.CAuthority
import com.itspub.framework.service.BaseService
import org.springframework.stereotype.Repository

/**
 * Created by Administrator on 2017/1/20.
 */
@Repository(value = "CAuthorityDaoImpl")
class CAuthorityDaoImpl : BaseService(), CAuthorityDao {
    override fun listAll(): List<CAuthority> {
        return sqlDao.listByAliasToBean(CAuthority::class.java, "select * from CAuthority" , arrayOf())
    }
}