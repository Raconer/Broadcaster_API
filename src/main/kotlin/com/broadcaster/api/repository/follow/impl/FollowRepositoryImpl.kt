package com.broadcaster.api.repository.follow.impl

import com.broadcaster.api.entity.follow.QFollow
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class FollowRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun getFollowByBroadcastIdAndUserEmail(broadcastId: Long, email: String): Long? {
        val qFollow = QFollow.follow
        return this.jpaQueryFactory
            .select(qFollow.users.id)
            .from(qFollow)
            .where(qFollow.broadcast.id.eq(broadcastId)
                .and(qFollow.users.email.eq(email)))
            .fetchOne()
    }
}