package com.broadcaster.api.dto.broadcast

import java.time.LocalDateTime

data class BroadcastDataDTO(
    var id:Long,
    var name:String,
    var djName: String, //
    var regDate:LocalDateTime
)