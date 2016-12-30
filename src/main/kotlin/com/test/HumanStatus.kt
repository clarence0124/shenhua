package com.test

import com.itspub.base.EnumType
import com.itspub.base.Enumerated

/**
 * Created by Administrator on 2016/12/8.
 */
@Enumerated(EnumType.ORDINAL)
enum class HumanStatus {
    HEALTH, ILL, DEATH
}