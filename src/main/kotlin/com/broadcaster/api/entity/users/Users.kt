package com.broadcaster.api.entity.users

import com.broadcaster.api.entity.Common
import com.broadcaster.api.entity.follow.Follow
import javax.persistence.*

@Entity
@Table(name = "users")
class Users (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,
    @Column(nullable = false, unique = true)
    var email:String? = null,
    @Column (nullable = false)
    var password:String? = null,
    @Column(nullable = false)
    var name:String? = null,
    @OneToMany(mappedBy = "users")
    var follows: MutableList<Follow> = ArrayList()
): Common(){
}