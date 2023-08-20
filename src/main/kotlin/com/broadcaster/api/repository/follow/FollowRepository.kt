package com.broadcaster.api.repository.follow

import com.broadcaster.api.entity.follow.Follow
import com.broadcaster.api.entity.follow.FollowId
import org.springframework.data.jpa.repository.JpaRepository

interface FollowRepository:JpaRepository<Follow, FollowId> {
    fun findByBroadcast_IdAndUsers_Email(broadcastId:Long, usersEmail:String):Follow?
}