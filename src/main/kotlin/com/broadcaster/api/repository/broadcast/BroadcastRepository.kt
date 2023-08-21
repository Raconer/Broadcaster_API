package com.broadcaster.api.repository.broadcast

import com.broadcaster.api.entity.broadcast.Broadcast
import org.springframework.data.jpa.repository.JpaRepository

interface BroadcastRepository : JpaRepository<Broadcast, Int> {
   fun findByUsersEmail(email:String):Broadcast
}