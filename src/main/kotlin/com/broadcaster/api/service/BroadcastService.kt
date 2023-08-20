package com.broadcaster.api.service

import com.broadcaster.api.dto.sign.SignDTO
import com.broadcaster.api.entity.broadcast.Broadcast
import com.broadcaster.api.entity.users.Users
import com.broadcaster.api.repository.broadcast.BroadcastRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class BroadcastService(
    private val broadcastRepository: BroadcastRepository,
    private val userService: UserService
) {

    @Transactional(isolation = Isolation.READ_COMMITTED)
    fun insert(signDTO: SignDTO) {
        val users: Users = this.userService.getByEmail(signDTO.username)

        val broadcast = Broadcast(
            users = users,
            name = signDTO.username
        )

        this.broadcastRepository.save(broadcast)
    }

    @Transactional(readOnly = true)
    fun getList(): List<Broadcast> {
        return this.broadcastRepository.findAll()
    }
}