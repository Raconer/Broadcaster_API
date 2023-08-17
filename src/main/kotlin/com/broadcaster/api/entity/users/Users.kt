package com.broadcaster.api.entity.users

import com.broadcaster.api.entity.Common
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

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