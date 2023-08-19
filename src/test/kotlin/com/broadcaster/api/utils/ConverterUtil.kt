package com.broadcaster.api.utils

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject

class ConverterUtil {
    companion object{

        private val objectMapper = ObjectMapper()
        fun getJsonString(data: Any?): String? {
            var jsonBody: String? = null
            try {
                jsonBody = this.objectMapper.writeValueAsString(data)
            } catch (e: JsonProcessingException) {
                e.printStackTrace()
            }
            return jsonBody
        }
        fun convertStrJsonObj(response: String): JSONObject {
            val data = JSONObject(response)
            return JSONObject(data.get("result").toString())
        }
    }
}