package com.broadcaster.api.entity.users

import com.broadcaster.api.entity.Common
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class Users (
    @Id
    var id:String,
    @Column(nullable = false)
    var email:String,
    @Column (nullable = false)
    var password:String,
    @Column(nullable = false)
    var name:String
): Common(){
}