package com.broadcaster.api.entity.follow

import com.broadcaster.api.entity.Common
import com.broadcaster.api.entity.broadcast.Broadcast
import com.broadcaster.api.entity.users.Users
import javax.persistence.*

@Entity
@IdClass(FollowId::class)
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
    @Column(nullable = false)
    var status: String
):Common()