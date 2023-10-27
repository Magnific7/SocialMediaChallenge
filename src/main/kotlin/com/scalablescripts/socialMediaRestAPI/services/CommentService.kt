package com.scalablescripts.socialMediaRestAPI.services

import com.scalablescripts.socialMediaRestAPI.dtos.ApiException
import com.scalablescripts.socialMediaRestAPI.models.database.Comment
import com.scalablescripts.socialMediaRestAPI.models.database.Post
import com.scalablescripts.socialMediaRestAPI.models.database.User
import com.scalablescripts.socialMediaRestAPI.repositories.CommentRepo
import com.scalablescripts.socialMediaRestAPI.repositories.PostRepo
import com.scalablescripts.socialMediaRestAPI.repositories.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepo: CommentRepo,
    private val postRepo: PostRepo,

) {
    fun findById(id: Long): Comment? {
        return commentRepo.findByIdOrNull(id)
    }

    fun findByUser(user: User): List<Comment> {
        return commentRepo.findByCommenter(user)
    }

    fun delete(comment: Comment) {
        return commentRepo.delete(comment)
    }
    fun createComment(comment: Comment): Comment {
        return commentRepo.save(comment)
    }

    fun getCommentById(commentId: Long): Comment {
        return commentRepo.findById(commentId)
            .orElseThrow {
                throw ApiException(404, "Comment Not Found")
            }
    }

    fun updateComment(postId: Long, commentId: Long, updatedComment: String): Comment {
        // Check if the post with the given postId exists
        val post = postRepo.findById(postId).orElseThrow { throw ApiException(404, "Post Not Found") }

        // Check if the comment with the given commentId exists within the specified post
        val comment = findById(postId)
            ?: throw ApiException(404, "Comment Not Found")

        // Update the comment properties as needed
        comment.comment = updatedComment

        // Save the updated comment
        return commentRepo.save(comment)
    }

    fun deleteComment(commentId: Long) {
        if (commentRepo.existsById(commentId)) {
            commentRepo.deleteById(commentId)
        } else {
            throw ApiException(404, "Comment Not Found")
        }
    }

    fun getCommentsByPost(post: Post): List<Comment> {
        return commentRepo.findByPost(post)
    }

    fun getCommentsByUser(user: User): List<Comment> {
        return commentRepo.findByCommenter(user)
    }
    fun isCommenter(commentId: Long, authentication: Authentication): Boolean {
        // Retrieve the comment based on commentId
        val comment = commentRepo.findById(commentId)
            .orElseThrow { throw ApiException(404, "Comment Not Found") }

        // Retrieve the user from authentication
        val authenticatedUser = authentication.principal as User

        // Check if the authenticated user is the commenter of the comment
        return authenticatedUser.id == comment.commenter.id
    }
}
