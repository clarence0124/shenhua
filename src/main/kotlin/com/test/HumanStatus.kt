package com.test

import com.itspub.framework.convert.EnumType
import com.itspub.framework.convert.annotation.Enumerated

/**
 * Created by Administrator on 2016/12/8.
 */
@Enumerated(EnumType.ORDINAL)
enum class HumanStatus {
    HEALTH, ILL, DEATH
}