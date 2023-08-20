package com.broadcaster.api.dto.follow

import com.broadcaster.api.constant.FollowStatus
import javax.validation.constraints.NotNull

data class FollowUpdateDTO (
    @field:NotNull
    var broadcastId:Long,
    @field:NotNull
    var followStatus: FollowStatus
)