package com.broadcaster.api.controller

import com.broadcaster.api.dto.Broadcast
import com.broadcaster.api.utils.JwtUtil
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
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
class BroadcastControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val jwtUtil: JwtUtil
)  {
    private val PATH:String = "/broadcast"
    private lateinit var token:String

    @BeforeEach
    fun setUpEach(){
        this.token = this.jwtUtil.create(Broadcast.EMAIL)
    }

    @Test
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
}