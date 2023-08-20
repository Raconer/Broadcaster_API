package com.broadcaster.api.dto

import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

class Broadcast {
    companion object{
        private var params: MultiValueMap<String, String> = LinkedMultiValueMap()
        val EMAIL = "test11@email.com"

        fun getSearchData(): MultiValueMap<String, String> {
            this.params.add("page", 1.toString())
            this.params.add("size", 30.toString())
            return params
        }
    }

}