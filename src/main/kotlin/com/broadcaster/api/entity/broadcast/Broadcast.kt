package com.broadcaster.api.entity.broadcast

import com.broadcaster.api.entity.Common
import com.broadcaster.api.entity.users.Users
import javax.persistence.*

@Entity
@Table(name = "broadcast")
class Broadcast(
    @Id
    var id:String,

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var users: Users,
    @Column(nullable = false)
    var name: String
): Common(){
}