package com.broadcaster.api.common.response

import java.time.LocalDate

data class ResponseMsg(
    val date:LocalDate = LocalDate.now(),
    var result: Any? = null

)