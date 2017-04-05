package com.itspub.core.service

import com.itspub.core.dao.AuthorityDao
import com.itspub.core.vo.Authority
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

/**
 * Created by Administrator on 2017/1/20.
 */
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Service
open class AuthorityServiceImpl @Autowired constructor(@Autowired var authorityDao: AuthorityDao) : AuthorityService {

    override fun listAll(): java.util.List<Authority> {
        return authorityDao.listAll()
    }
}