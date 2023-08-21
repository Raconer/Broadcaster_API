package com.broadcaster.api.service

import org.redisson.api.RScoredSortedSet
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Service


@Service
class RedisService(
    private val redissonClient: RedissonClient
) {
    private val REDISSION_KEY:String = "broadcastSortedSet"
    fun setSortBroadCast(broadcastId:Long, addition:Int){
        val sortedSet: RScoredSortedSet<String> = redissonClient.getScoredSortedSet("${this.REDISSION_KEY}")
        sortedSet.addScore("DJ$broadcastId", addition )
   }

    fun getSortBroadCast(broadcastId:Long):Long{
        val getSorted: RScoredSortedSet<String> = redissonClient.getScoredSortedSet("${this.REDISSION_KEY}")
        val followCnt = getSorted.getScore("DJ$broadcastId")?:0

        return followCnt.toLong()
    }

}