package com.test

import com.itspub.framework.dao.ISqlDao
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

/**
 * Created by Administrator on 2016/12/17.
 */
@Transactional
@Service
open class TestService {

    @Resource
    var sqlDao: ISqlDao? = null

    open fun test1(): List<User>? {
        return sqlDao?.listByAliasToBean(User::class.java, "select cast(id as varchar) id, name, 2 'status' from userinfos")
    }
}