package com.broadcaster.api.repository.broadcast.impl

import com.broadcaster.api.common.exception.CustomException
import com.broadcaster.api.constant.FollowStatus
import com.broadcaster.api.dto.PageDTO
import com.broadcaster.api.dto.broadcast.BroadcastDataDTO
import com.broadcaster.api.dto.broadcast.BroadcastDetailDTO
import com.broadcaster.api.dto.users.UsersDataDTO
import com.broadcaster.api.entity.broadcast.Broadcast
import com.broadcaster.api.entity.broadcast.QBroadcast
import com.broadcaster.api.entity.follow.QFollow
import com.broadcaster.api.entity.users.QUsers
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class BroadcastRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) {

    fun getBroadcast(id: Long, userId: Long): BroadcastDetailDTO {
        val qBroadcast = QBroadcast.broadcast
        val qUsers = QUsers.users
        val qFollow = QFollow.follow
        val broadcastDetailDTO: BroadcastDetailDTO = this.jpaQueryFactory
            .select(
                Projections.constructor(
                    BroadcastDetailDTO::class.java,
                    qBroadcast.id,
                    qBroadcast.name,
                    qUsers.id,
                    qUsers.name
                )
            ).from(qBroadcast)
            .join(qBroadcast.users, qUsers)
            .leftJoin(qFollow)
            .on(qBroadcast.id.eq(qFollow.broadcast.id).and(qFollow.users.id.eq(userId)))
            .where(
                qBroadcast.id.eq(id)
                    .and(
                        qFollow.broadcastStatus.eq(FollowStatus.NORMAL).and(qFollow.userStatus.eq(FollowStatus.NORMAL))
                            .or(qFollow.broadcast.id.isNull)
                    )
            )
            .fetchOne() ?: throw CustomException("NOT FOUND BROADCAST DATA")

        if (broadcastDetailDTO.userId == userId) {
            val qListener = QUsers.users
            val qListenerFollow = QFollow.follow
            broadcastDetailDTO.fanList = this.jpaQueryFactory.select(
                Projections.constructor(
                    UsersDataDTO::class.java,
                    qListener.id,
                    qListener.name
                )
            ).from(qListenerFollow)
            .innerJoin(qListenerFollow.users, qListener)
                .where(qListenerFollow.broadcast.id.eq(broadcastDetailDTO.id))
                .fetch()
        }

        return broadcastDetailDTO
    }

    fun getByEmail(email: String): Broadcast? {
        val qBroadcast = QBroadcast.broadcast
        val qUsers = QUsers.users
        return this.jpaQueryFactory
            .select(qBroadcast)
            .from(qBroadcast)
            .join(qBroadcast.users, qUsers)
            .where(qUsers.email.eq(email))
            .fetchOne()
    }

    fun getList(pageDTO: PageDTO): List<BroadcastDataDTO> {

        val pageable = PageRequest.of(pageDTO.page, pageDTO.size)

        val qBroadcast = QBroadcast.broadcast
        val qUsers = QUsers.users

        return this.jpaQueryFactory
            .select(
                Projections.constructor(
                    BroadcastDataDTO::class.java,
                    qBroadcast.id,
                    qBroadcast.name,
                    qUsers.name,
                    qBroadcast.regDate
                )
            )
            .from(qBroadcast)
            .join(qBroadcast.users, qUsers)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(qBroadcast.regDate.desc()).fetch()
    }

}