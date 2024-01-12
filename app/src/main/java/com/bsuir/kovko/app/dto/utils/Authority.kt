package com.bsuir.kovko.app.dto.utils

object Authority {
    val USER_AUTHORITIES = arrayOf("user:read")
    val ADMIN_AUTHORITIES = arrayOf("user:read", "user:create")
}
