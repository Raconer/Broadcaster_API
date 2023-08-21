package com.broadcaster.api.dto

import com.broadcaster.api.constant.FollowStatus
import com.broadcaster.api.dto.broadcast.BroadcastUpdateDTO
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

class Broadcast {
    companion object {
        private var params: MultiValueMap<String, String> = LinkedMultiValueMap()
        val EMAIL = "test11@email.com"
        val DJ_EMAIL = "test1@email.com"
        val DJ_EMAIL_2 = "test2@email.com"
        val DJ_ID:Long = 1L

        val FOLLOW_EMAIL = "test12@email.com"
        val FOLLOW_BROADCAST_BLOCK_ID = 2L
        val FOLLOW_USER_BLOCK_ID = 3L
        val FOLLOW_MULTIPLE_BLOCK_ID = 4L
        val FOLLOW_USRE_ID = 12L
        fun getSearchData(): MultiValueMap<String, String> {
            this.params.add("page", 1.toString())
            this.params.add("size", 30.toString())
            return params
        }

        fun getBlockData(): BroadcastUpdateDTO {
            return BroadcastUpdateDTO(11L, FollowStatus.BLOCK)
        }
    }

}