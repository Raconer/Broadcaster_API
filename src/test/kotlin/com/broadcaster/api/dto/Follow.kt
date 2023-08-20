package com.broadcaster.api.dto

import com.broadcaster.api.dto.follow.FollowDTO

class Follow {
    companion object{
        val EMAIL = "test11@email.com"
        val BROADCAST_ID:Long = 1L

        fun getData(): FollowDTO {
            return FollowDTO(
                this.BROADCAST_ID
            )
        }

    }
}