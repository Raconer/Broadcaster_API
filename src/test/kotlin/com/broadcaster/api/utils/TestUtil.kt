package com.broadcaster.api.utils

import com.fasterxml.jackson.databind.ObjectMapper

class TestUtil {
    companion object {

        private val objectMapper = ObjectMapper()

        fun getJsonString(data:Any):String{
            return this.objectMapper.writeValueAsString(data)
        }

        fun getResult(value:String):HashMap<String, Any>{
            val data = this.objectMapper.readValue(value, HashMap::class.java)
            return data["result"] as HashMap<String, Any>
        }
    }
}