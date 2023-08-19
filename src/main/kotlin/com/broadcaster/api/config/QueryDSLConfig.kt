package com.broadcaster.api.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager

@Configuration
class QueryDSLConfig (
    private val entityManager: EntityManager
){

    @Bean
    fun queryFactory(): JPAQueryFactory? {
        return JPAQueryFactory(entityManager)
    }
}