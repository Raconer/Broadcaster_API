package com.broadcaster.api.entity.broadcast

import com.broadcaster.api.entity.Common
import com.broadcaster.api.entity.follow.Follow
import com.broadcaster.api.entity.users.Users
import javax.persistence.*

@Entity
@Table(name = "broadcast")
class Broadcast(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    var users: Users? = null,
    @Column(nullable = false)
    var name: String? = null,
    @OneToMany(mappedBy = "broadcast")
    var follows: MutableList<Follow> = ArrayList()
): Common(){
}