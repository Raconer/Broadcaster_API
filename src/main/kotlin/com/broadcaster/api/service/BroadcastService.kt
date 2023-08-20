package com.broadcaster.api.service

import com.broadcaster.api.common.exception.CustomException
import com.broadcaster.api.dto.PageDTO
import com.broadcaster.api.dto.broadcast.BroadcastUpdateDTO
import com.broadcaster.api.dto.broadcast.BroadcastDataDTO
import com.broadcaster.api.dto.sign.SignDTO
import com.broadcaster.api.entity.broadcast.Broadcast
import com.broadcaster.api.entity.follow.Follow
import com.broadcaster.api.entity.users.Users
import com.broadcaster.api.repository.broadcast.BroadcastRepository
import com.broadcaster.api.repository.broadcast.impl.BroadcastRepositoryImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class BroadcastService(
    private val broadcastRepositoryImpl: BroadcastRepositoryImpl,
    private val broadcastRepository: BroadcastRepository,
    private val userService: UserService,
    private val followService: FollowService
) {

    @Transactional(
        isolation = Isolation.READ_COMMITTED,
        rollbackFor = [Exception::class]
    )
    fun insert(signDTO: SignDTO) {
        val users: Users = this.userService.getByEmail(signDTO.username)

        val broadcast = Broadcast(
            users = users,
            name = signDTO.username
        )

        this.broadcastRepository.save(broadcast)
    }

    @Transactional(readOnly = true)
    fun getList(pageDTO: PageDTO): List<BroadcastDataDTO> {
        return this.broadcastRepositoryImpl.getList(pageDTO)
    }

    @Transactional(
        isolation = Isolation.READ_COMMITTED,
        rollbackFor = [Exception::class]
    )
    fun update(broadcastUpdateDTO: BroadcastUpdateDTO, email: String) {
        val broadcast = this.broadcastRepositoryImpl.getByEmail(email)!!

        var follow:Follow? = this.followService.getByIds(broadcast.id!!, broadcastUpdateDTO.usersId)

        if(follow == null){
            val users = Users(broadcastUpdateDTO.usersId)
            follow = Follow(broadcast, users)
        }

        follow.broadcastStatus = broadcastUpdateDTO.followStatus
        this.followService.update(follow)
    }
}