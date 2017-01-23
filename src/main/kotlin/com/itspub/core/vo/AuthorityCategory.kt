package com.itspub.core.vo

import com.itspub.framework.convert.EnumType
import com.itspub.framework.convert.annotation.Enumerated

/**
 * Created by Administrator on 2017/1/20.
 */
@Enumerated(EnumType.ORDINAL)
enum class AuthorityCategory {
    PAGE, MENU, DATA
}