package com.broadcaster.api.controller

import com.broadcaster.api.dto.SignUp
import com.broadcaster.api.utils.ConverterUtil
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = ["default"])
class SignControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) {

    private val PATH:String = "/sign"

    @Test
    fun signUp() {
        // GIVEN
        val signUpDTO = SignUp.getData()
        val jsonBody:String = ConverterUtil.getJsonString(signUpDTO)!!;
        // WHEN & THEN
        mockMvc.perform(
            MockMvcRequestBuilders.post("${this.PATH}/up")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBody)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

}