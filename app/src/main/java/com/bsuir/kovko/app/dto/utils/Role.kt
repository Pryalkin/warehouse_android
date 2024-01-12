package com.bsuir.kovko.app.dto.utils

import com.bsuir.kovko.app.dto.utils.Authority.ADMIN_AUTHORITIES
import com.bsuir.kovko.app.dto.utils.Authority.USER_AUTHORITIES

enum class Role(vararg authorities: String) {
    ROLE_USER(*USER_AUTHORITIES),
    ROLE_ADMIN(*ADMIN_AUTHORITIES);

    val authorities: Array<String>

    init {
        this.authorities = authorities as Array<String>
    }
}
