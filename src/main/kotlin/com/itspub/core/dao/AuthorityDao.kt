package com.itspub.core.dao

import com.itspub.core.vo.Authority
import com.itspub.raindrop.NamedQuery
import com.test.User
import java.util.List

/**
 * Created by Administrator on 2017/1/20.
 */
@NamedQuery(namespace = "authority")
interface AuthorityDao {

    @NamedQuery()
    fun listAll(): List<Authority>

    @NamedQuery(value = "test")
    fun listAll2(str: String): List<Authority>

    @NamedQuery
    fun getHelloWorld(hello: String, world: Int): String

    @NamedQuery
    fun getUserByName(name: String): User
}