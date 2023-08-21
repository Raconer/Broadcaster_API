package com.broadcaster.api.dto.broadcast

import com.broadcaster.api.dto.users.UsersDataDTO

data class BroadcastDetailDTO(
    var id:Long,
    var name:String,
    var userId:Long,
    var username:String,
    var followCount:Long,
    var fanList:List<UsersDataDTO>
){
    constructor(id: Long, name: String, userId:Long, username: String) : this(id, name, userId, username, 0, arrayListOf())
}