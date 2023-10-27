package com.scalablescripts.socialMediaRestAPI.repositories

import com.scalablescripts.socialMediaRestAPI.models.database.Like
import com.scalablescripts.socialMediaRestAPI.models.database.Post
import com.scalablescripts.socialMediaRestAPI.models.database.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface LikeRepo : JpaRepository<Like, Long> {
    fun findByPost(post: Post): List<Like>

    fun findByPostAndLiker(post: Post, liker: User): Like?

    override fun deleteById(likeId: Long)

}



