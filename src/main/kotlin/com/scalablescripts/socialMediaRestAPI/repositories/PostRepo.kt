package com.scalablescripts.socialMediaRestAPI.repositories
import com.scalablescripts.socialMediaRestAPI.models.database.Post
import com.scalablescripts.socialMediaRestAPI.models.database.User
import org.springframework.data.repository.CrudRepository

interface PostRepo : CrudRepository<Post, Long> {
    // Find posts by author
    fun findByAuthor(author: User): List<Post>

    // Find posts by title
    fun findByTitleContaining(title: String): List<Post>

    override fun deleteById(postId: Long)

}

