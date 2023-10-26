package com.scalablescripts.socialMediaRestAPI.repositories

import com.scalablescripts.socialMediaRestAPI.models.database.User
import org.springframework.data.repository.CrudRepository

interface UserRepo : CrudRepository<User, Long> {
    fun findByName(name: String): User?
    fun existsByName(name: String): Boolean
}
