package com.itspub.framework.service

import com.itspub.framework.dao.SqlDao
import com.itspub.framework.dao.SqlDaoMock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

/**
 * Created by Administrator on 2017/1/20.
 */
abstract class BaseService(sqlDao: SqlDao = SqlDaoMock.INSTANCE) {

    @Autowired
    @Qualifier("sqlDao")
    protected var sqlDao: SqlDao = sqlDao
}