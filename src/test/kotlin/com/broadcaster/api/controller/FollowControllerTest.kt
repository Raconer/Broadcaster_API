package com.broadcaster.api.controller

import com.broadcaster.api.dto.Follow
import com.broadcaster.api.utils.ConverterUtil
import com.broadcaster.api.utils.JwtUtil
import org.junit.jupiter.api.BeforeEach
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
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles(profiles = ["test","default"])
class FollowControllerTest  @Autowired constructor(
    private val mockMvc: MockMvc,
    private val jwtUtil: JwtUtil
) {

    private val PATH:String = "/follow"
    private lateinit var token:String

    @BeforeEach
    fun setUpEach(){
        this.token = this.jwtUtil.create(Follow.EMAIL)
    }

    @Test
    @DisplayName("팔로우 추가 테스트")
    fun insert() {
        // GIVEN
        var followDTO = Follow.getData()
        val jsonBody: String = ConverterUtil.getJsonString(followDTO)!!
        // WHEN & THEN
        mockMvc.perform(
            MockMvcRequestBuilders.post(this.PATH)
                .header("Authorization", "Bearer ${this.token}")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBody)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @DisplayName("Broadcast Block 테스트(USER(pk:11) BLOCK -> DJ(pk:1))")
    fun update() {
        // GIVEN
        var broadcastBlockDTO = Follow.getBlockData()
        val jsonBody: String = ConverterUtil.getJsonString(broadcastBlockDTO)!!
        // WHEN & THEN
        mockMvc.perform(
            MockMvcRequestBuilders.put(this.PATH)
                .header("Authorization", "Bearer $token")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBody)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }
}