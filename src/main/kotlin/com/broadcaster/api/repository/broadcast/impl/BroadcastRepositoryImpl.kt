package com.broadcaster.api.repository.broadcast.impl

import com.broadcaster.api.entity.broadcast.QBroadcast
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class BroadcastRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun get() {
        val qBroadcast = QBroadcast.broadcast
        this.jpaQueryFactory.selectFrom(qBroadcast).fetch()

    }
}