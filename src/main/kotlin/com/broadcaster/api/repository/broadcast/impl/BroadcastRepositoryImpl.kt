package com.broadcaster.api.repository.broadcast.impl

import com.broadcaster.api.dto.PageDTO
import com.broadcaster.api.dto.broadcast.BroadcastDataDTO
import com.broadcaster.api.entity.broadcast.QBroadcast
import com.broadcaster.api.entity.users.QUsers
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class BroadcastRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun getList(pageDTO: PageDTO):List<BroadcastDataDTO> {

        val pageable = PageRequest.of(pageDTO.page, pageDTO.size)

        val qBroadcast = QBroadcast.broadcast
        val qUsers = QUsers.users

         return this.jpaQueryFactory
             .select(
                 Projections.constructor(
                 BroadcastDataDTO::class.java,
                 qBroadcast.id,
                 qBroadcast.name,
                 qUsers.name.`as`("djName"),
                 qBroadcast.regDate
             ))
            .from(qBroadcast)
            .join(qBroadcast.users, qUsers)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(qBroadcast.regDate.desc()).fetch()
    }
}