package com.scalablescripts.socialMediaRestAPI.repositories
import com.scalablescripts.socialMediaRestAPI.models.database.Comment
import com.scalablescripts.socialMediaRestAPI.models.database.Post
import com.scalablescripts.socialMediaRestAPI.models.database.User
import org.springframework.data.repository.CrudRepository

interface CommentRepo : CrudRepository<Comment, Long>  {

    // Find posts by commenter
    fun findByCommenter(commenter: User): List<Comment>

    // Find comments by post
    fun findByPost(post: Post): List<Comment>

}



