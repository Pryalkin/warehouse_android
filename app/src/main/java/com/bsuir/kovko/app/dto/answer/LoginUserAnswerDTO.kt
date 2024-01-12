package com.bsuir.kovko.app.dto.answer

data class LoginUserAnswerDTO(
    val username: String,
    val role: String,
    val authorities: Array<String>
)
