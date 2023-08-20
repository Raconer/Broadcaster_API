package com.broadcaster.api.dto.broadcast

import com.broadcaster.api.constant.FollowStatus
import javax.validation.constraints.NotNull

data class BroadcastUpdateDTO(
    @field:NotNull var usersId:Long,
    @field:NotNull var followStatus: FollowStatus
)
