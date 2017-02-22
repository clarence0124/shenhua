package com.itspub.core.dao

import com.itspub.core.vo.Authority
import com.itspub.framework.service.BaseService
import com.itspub.raindrop.NamedQuery
import com.itspub.raindrop.NamedQueryConfig
import com.test.User
import java.util.List
import org.springframework.stereotype.Repository

@Repository
class AuthorityDaoImpl constructor(): BaseService(), AuthorityDao {
    override fun listAll(): List<Authority> {
        return sqlDao.listByAliasToBean(Authority::class.java, NamedQueryConfig.get("listAll"), arrayOf<Any>())
    }
    override fun listAll2(str: String): List<Authority> {
        return sqlDao.listByAliasToBean(Authority::class.java, NamedQueryConfig.get("test"), arrayOf<Any>(str))
    }
    override fun getHelloWorld(hello: String, world: Int): String {
        return sqlDao.getAutoCast(NamedQueryConfig.get("getHelloWorld"), arrayOf<Any>(hello, world))
    }
    override fun getUserByName(name: String): User {
        return sqlDao.getAutoCast(NamedQueryConfig.get("getUserByName"), arrayOf<Any>(name))
    }

}