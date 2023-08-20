package com.broadcaster.api.entity.follow

import java.io.Serializable

data class FollowId(
    val broadcast:Long,
    val users:Long
) : Serializable {
    constructor() : this(0L, 0L)
}
