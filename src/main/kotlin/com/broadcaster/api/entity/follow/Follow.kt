package com.broadcaster.api.entity.follow

import com.broadcaster.api.constant.FollowStatus
import com.broadcaster.api.entity.Common
import com.broadcaster.api.entity.broadcast.Broadcast
import com.broadcaster.api.entity.users.Users
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@IdClass(FollowId::class)
@DynamicUpdate
@Table(name = "follow")
class Follow (
    @Id
    @ManyToOne
    @JoinColumn(name = "broadcast_id", referencedColumnName = "id")
    val broadcast: Broadcast,
    @Id
    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    val users: Users,

    @Column(nullable = false, )
    var broadcastStatus: FollowStatus? = FollowStatus.NORMAL,

    @Column(nullable = false)
    var userStatus: FollowStatus? = FollowStatus.NORMAL

):Common(){
    fun isStatus():Boolean{
        return this.broadcastStatus == FollowStatus.NORMAL && this.userStatus == FollowStatus.NORMAL
    }
}