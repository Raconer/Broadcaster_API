package com.broadcaster.api.controller

import com.broadcaster.api.dto.Broadcast
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
@ActiveProfiles(profiles = ["test", "default"])
class BroadcastControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val jwtUtil: JwtUtil
) {
    private val PATH: String = "/broadcast"
    private lateinit var token: String

    @BeforeEach
    fun setUpEach() {
        this.token = this.jwtUtil.create(Broadcast.EMAIL)
    }

    @Test
    @DisplayName("방송 등록 테스트")
    fun insert() {
        // GIVEN
        // WHEN & THEN
        mockMvc.perform(
            MockMvcRequestBuilders.post(this.PATH)
                .header("Authorization", "Bearer ${this.token}")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @DisplayName("방속국 프로필 조회")
    fun get() {
        // GIVEN
        val followToken: String = this.jwtUtil.create(Broadcast.FOLLOW_EMAIL)
        val broadcastId:Long = Broadcast.DJ_ID

        // WHEN & THEN
        mockMvc.perform(
            MockMvcRequestBuilders.get("${this.PATH}/$broadcastId")
                .header("Authorization", "Bearer $followToken")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @DisplayName("방속국 프로필 조회 - 방송국에서 차단시")
    fun getBlockBroadcast() {
        // GIVEN
        val followToken: String = this.jwtUtil.create(Broadcast.FOLLOW_EMAIL)
        val broadcastId:Long = Broadcast.FOLLOW_BROADCAST_BLOCK_ID

        // WHEN & THEN
        mockMvc.perform(
            MockMvcRequestBuilders.get("${this.PATH}/$broadcastId")
                .header("Authorization", "Bearer $followToken")
        ).andExpect(MockMvcResultMatchers.status().isInternalServerError)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @DisplayName("방속국 프로필 조회 - 유저가 차단시")
    fun getBlockUser() {
        // GIVEN
        val followToken: String = this.jwtUtil.create(Broadcast.FOLLOW_EMAIL)
        val broadcastId:Long = Broadcast.FOLLOW_USER_BLOCK_ID

        // WHEN & THEN
        mockMvc.perform(
            MockMvcRequestBuilders.get("${this.PATH}/$broadcastId")
                .header("Authorization", "Bearer $followToken")
        ).andExpect(MockMvcResultMatchers.status().isInternalServerError)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @DisplayName("방속국 프로필 조회 - 둘다 차단시")
    fun getBlockMulti() {
        // GIVEN
        val followToken: String = this.jwtUtil.create(Broadcast.FOLLOW_EMAIL)
        val broadcastId:Long = Broadcast.FOLLOW_MULTIPLE_BLOCK_ID

        // WHEN & THEN
        mockMvc.perform(
            MockMvcRequestBuilders.get("${this.PATH}/$broadcastId")
                .header("Authorization", "Bearer $followToken")
        ).andExpect(MockMvcResultMatchers.status().isInternalServerError)
            .andDo(MockMvcResultHandlers.print())
    }


    @Test
    @DisplayName("방송국 리스트 테스트")
    fun getList() {
        // GIVEN
        var params = Broadcast.getSearchData()
        // WHEN & THEN
        mockMvc.perform(
            MockMvcRequestBuilders.get(this.PATH)
                .header("Authorization", "Bearer ${this.token}")
                .params(params)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @DisplayName("유저 Block 테스트 (DJ(pk:1) BLOCK -> USER(pk:11))")
    fun update() {
        // GIVEN
        val djToken: String = this.jwtUtil.create(Broadcast.DJ_EMAIL)

        var broadcastBlockDTO = Broadcast.getBlockData()
        val jsonBody: String = ConverterUtil.getJsonString(broadcastBlockDTO)!!
        // WHEN & THEN
        mockMvc.perform(
            MockMvcRequestBuilders.put(this.PATH)
                .header("Authorization", "Bearer $djToken")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBody)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }
}