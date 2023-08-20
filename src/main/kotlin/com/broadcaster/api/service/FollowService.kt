package com.broadcaster.api.service

import com.broadcaster.api.common.exception.CustomException
import com.broadcaster.api.constant.FollowStatus
import com.broadcaster.api.dto.follow.FollowDTO
import com.broadcaster.api.entity.broadcast.Broadcast
import com.broadcaster.api.entity.follow.Follow
import com.broadcaster.api.repository.follow.FollowRepository
import com.broadcaster.api.repository.follow.impl.FollowRepositoryImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FollowService(
    private val followRepository: FollowRepository,
    private val followRepositoryImpl: FollowRepositoryImpl,
    private val userService: UserService,
) {
    @Transactional(
        isolation = Isolation.READ_COMMITTED,
        rollbackFor = [Exception::class]
    )
    fun insert(followDTO: FollowDTO, email: String) {
        this.followRepositoryImpl.getFollowByBroadcastIdAndUserEmail(followDTO.broadcastId!!, email)?.let{
            throw CustomException("Already Data")
        }

        val users = this.userService.getByEmail(email)
        val broadcast = Broadcast(id = followDTO.broadcastId!!)

        val follow = Follow(broadcast, users, FollowStatus.NORMAL, FollowStatus.NORMAL)
        users.follows.add(follow)
        broadcast.follows.add(follow)

        this.followRepository.saveAndFlush(follow)
    }
}