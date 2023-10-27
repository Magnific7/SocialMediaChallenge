package com.scalablescripts.socialMediaRestAPI.repositories

import com.scalablescripts.socialMediaRestAPI.models.database.Like
import com.scalablescripts.socialMediaRestAPI.models.database.Post
import org.springframework.data.repository.CrudRepository

interface LikeRepo : CrudRepository<Like, Long> {
    fun findByPost(post: Post): List<Like>
}


