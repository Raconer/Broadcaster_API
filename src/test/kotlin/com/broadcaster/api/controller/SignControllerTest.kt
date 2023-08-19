package com.broadcaster.api.controller

import com.broadcaster.api.dto.SignIn
import com.broadcaster.api.dto.SignUp
import com.broadcaster.api.dto.sign.SignInDTO
import com.broadcaster.api.utils.ConverterUtil
import org.junit.jupiter.api.DisplayName
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
@ActiveProfiles(profiles = ["test","default"])
class SignControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) {

    private val PATH: String = "/sign"

    @Test
    @DisplayName("회원 가입 테스트")
    fun signUpTest() {
        // GIVEN
        val signUpDTO = SignUp.getData()
        val jsonBody: String = ConverterUtil.getJsonString(signUpDTO)!!;
        // WHEN & THEN
        mockMvc.perform(
            MockMvcRequestBuilders.post("${this.PATH}/up")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBody)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @DisplayName("회원 가입 Validate 테스트")
    fun signUpValidateTest() {
        // GIVEN
        val signUpDTO = SignUp.getValidData()
        val jsonBody: String = ConverterUtil.getJsonString(signUpDTO)!!;
        // WHEN & THEN
        mockMvc.perform(
            MockMvcRequestBuilders.post("${this.PATH}/up")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBody)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @DisplayName("회원 가입 중복 테스트")
    fun signUpDuplicateTest() {
        // GIVEN
        val signUpDTO = SignUp.getDuplicateData()
        val jsonBody: String = ConverterUtil.getJsonString(signUpDTO)!!;
        // WHEN & THEN
        mockMvc.perform(
            MockMvcRequestBuilders.post("${this.PATH}/up")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBody)
        ).andExpect(MockMvcResultMatchers.status().isInternalServerError)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @DisplayName("로그인 테스트")
    fun signInTest() {
        // GIVEN
        val signUpDTO = SignIn.getData()
        val jsonBody: String = ConverterUtil.getJsonString(signUpDTO)!!;
        // WHEN & THEN
        mockMvc.perform(
            MockMvcRequestBuilders.post("${this.PATH}/in")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBody)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

}