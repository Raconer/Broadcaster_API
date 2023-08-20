package com.broadcaster.api.service

import org.redisson.api.RScoredSortedSet
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Service


@Service
class RedisService(
    private val redissonClient: RedissonClient
) {
    private val REDISSION_KEY:String = "broadcastSortedSet"
    fun sortSetBroadCast(broadcastId:Long, addition:Int){
        val sortedSet: RScoredSortedSet<String> = redissonClient.getScoredSortedSet("${this.REDISSION_KEY}")
        sortedSet.addScore("DJ$broadcastId", addition )
        this.redissonClient.shutdown()
    }

    fun sortGetBroadCast(broadcastId:Long):Long{
        val sortedSet: RScoredSortedSet<String> = redissonClient.getScoredSortedSet("${this.REDISSION_KEY}")
        val followCnt:Long = sortedSet.getScore("DJ$broadcastId").toLong()
        this.redissonClient.shutdown()
        return followCnt
    }

}