package com.broadcaster.api.entity.broadcast

import com.broadcaster.api.entity.Common
import com.broadcaster.api.entity.users.Users
import javax.persistence.*

@Entity
@Table(name = "broadcast")
class Broadcast(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int? = null,
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    var users: Users,
    @Column(nullable = false)
    var name: String
): Common(){
}