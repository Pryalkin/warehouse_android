package com.bsuir.kovko.app.dto.answer

data class ApplicationAnswerDTO(

    val id: Long,
    val number: String,
    val status: String,
    val message: String,
    val warehouseName: String,
    val file: String,
    val subjectAnswerDTO: SubjectAnswerDTO

)
