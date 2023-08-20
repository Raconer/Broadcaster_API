package com.broadcaster.api.service

import com.broadcaster.api.common.exception.CustomException
import com.broadcaster.api.constant.FollowStatus
import com.broadcaster.api.dto.follow.FollowDTO
import com.broadcaster.api.dto.follow.FollowUpdateDTO
import com.broadcaster.api.entity.broadcast.Broadcast
import com.broadcaster.api.entity.follow.Follow
import com.broadcaster.api.entity.users.Users
import com.broadcaster.api.repository.follow.FollowRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FollowService(
    private val followRepository: FollowRepository,
    private val userService: UserService,
    private val redisService: RedisService
) {
    @Transactional(
        isolation = Isolation.READ_COMMITTED,
        rollbackFor = [Exception::class]
    )
    fun insert(followDTO: FollowDTO, email: String) {
        this.followRepository.findByBroadcastIdAndUsersEmail(followDTO.broadcastId!!, email)?.let{
            throw CustomException("Already Data")
        }

        val users = this.userService.getByEmail(email)
        val broadcast = Broadcast(id = followDTO.broadcastId!!)

        val follow = Follow(broadcast, users)

        // users.follows.add(follow)
        // broadcast.follows.add(follow)

        this.followRepository.saveAndFlush(follow)
        this.redisService.sortSetBroadCast(broadcast.id!!, 1)
    }

    fun getByIds(broadcastId:Long, usersId:Long):Follow?{
        return this.followRepository.findByBroadcastIdAndUsersId(broadcastId, usersId)
    }

    fun getByIdAndEmail(broadcastId:Long, email:String):Follow?{
        return this.followRepository.findByBroadcastIdAndUsersEmail(broadcastId, email)
    }

    @Transactional(
        isolation = Isolation.READ_COMMITTED,
        rollbackFor = [Exception::class]
    )
    fun update(follow: Follow) {
        this.followRepository.saveAndFlush(follow)
    }

    @Transactional(
        isolation = Isolation.READ_COMMITTED,
        rollbackFor = [Exception::class]
    )
    fun updateByDTO(followUpdateDTO: FollowUpdateDTO, email:String) {
        var follow:Follow? = this.getByIdAndEmail(followUpdateDTO.broadcastId, email)
        if(follow == null){
            val broadcast = Broadcast(id = followUpdateDTO.broadcastId)
            val users = this.userService.getByEmail(email)
            follow = Follow(broadcast, users)
        }
        follow.userStatus = followUpdateDTO.followStatus

        this.followRepository.saveAndFlush(follow)
    }
}