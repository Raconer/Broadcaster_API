package com.broadcaster.api.repository.follow

import com.broadcaster.api.entity.follow.Follow
import com.broadcaster.api.entity.follow.FollowId
import org.springframework.data.jpa.repository.JpaRepository

interface FollowRepository:JpaRepository<Follow, FollowId> {
}