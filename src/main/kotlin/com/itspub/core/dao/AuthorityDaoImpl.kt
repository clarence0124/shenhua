package com.itspub.core.dao

import com.itspub.core.vo.Authority
import com.itspub.framework.service.BaseService
import org.springframework.stereotype.Repository

/**
 * Created by Administrator on 2017/1/20.
 */
@Repository
class AuthorityDaoImpl : BaseService(), AuthorityDao {
    override fun listAll(): List<Authority> {
        return sqlDao.listByAliasToBean(Authority::class.java, "select * from CAuthority", arrayOf())
    }
}