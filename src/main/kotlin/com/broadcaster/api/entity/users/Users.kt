package com.broadcaster.api.entity.users

import com.broadcaster.api.entity.Common
import javax.persistence.*

@Entity
@Table(name = "users")
class Users (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int? = null,
    @Column(nullable = false)
    var email:String,
    @Column (nullable = false)
    var password:String,
    @Column(nullable = false)
    var name:String
): Common(){
}