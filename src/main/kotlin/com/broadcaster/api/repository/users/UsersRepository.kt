package com.broadcaster.api.repository.users

import com.broadcaster.api.entity.users.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository:JpaRepository<Users, String> {
}