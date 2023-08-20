package com.broadcaster.api.dto

import com.broadcaster.api.constant.FollowStatus
import com.broadcaster.api.dto.follow.FollowDTO
import com.broadcaster.api.dto.follow.FollowUpdateDTO

class Follow {
    companion object{
        val EMAIL = "test11@email.com"
        val BROADCAST_ID:Long = 3L

        fun getData(): FollowDTO {
            return FollowDTO(
                this.BROADCAST_ID
            )
        }
        fun getBlockData(): FollowUpdateDTO {
            return FollowUpdateDTO(1L, FollowStatus.BLOCK)
        }
    }
}