package com.test

import com.itspub.base.ISqlDao
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
        return sqlDao?.listByAliasToBean("select cast(id as varchar) id, name from userinfos", null, User::class.java)
    }
}