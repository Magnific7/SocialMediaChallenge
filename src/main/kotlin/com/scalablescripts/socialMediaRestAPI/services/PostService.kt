package com.scalablescripts.socialMediaRestAPI.services

import com.scalablescripts.socialMediaRestAPI.dtos.ApiException
import com.scalablescripts.socialMediaRestAPI.dtos.PostDto
import com.scalablescripts.socialMediaRestAPI.helpers.err404
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.scalablescripts.socialMediaRestAPI.models.database.Post
import com.scalablescripts.socialMediaRestAPI.models.database.User
import com.scalablescripts.socialMediaRestAPI.repositories.PostRepo
import org.springframework.security.core.Authentication

@Service
class PostService {

    @Autowired
    private lateinit var postRepo: PostRepo

    fun createPost(post: Post): Post {
        return postRepo.save(post)
    }

    fun getAllPosts(): List<Post> {
        return postRepo.findAll().toList()
    }

    fun getPostById(postId: Long): Post {
        return postRepo.findById(postId)
            .orElseThrow {
                throw ApiException(404, "Post Not Found")
            }
    }

    fun updatePost(postId: Long, updatedPost: PostDto): Post {
        val post = postRepo.findById(postId).orElseThrow { throw ApiException(404, "Post Not Found") }

        // Update the post properties as needed
        post.title = updatedPost.title ?: post.title
        post.description = updatedPost.description ?: post.description

        return postRepo.save(post)
    }

    fun deletePost(postId: Long) {
        if (postRepo.existsById(postId)) {
            postRepo.deleteById(postId)
        } else {
            throw ApiException(404, "Post Not Found")
        }
    }

    fun searchPostsByTitle(title: String): List<Post> {
        return postRepo.findByTitleContaining(title)
    }

    fun getPostsByAuthor(author: User): List<Post> {
        return postRepo.findByAuthor(author)
    }
    fun isPostAuthor(postId: Long, authentication: Authentication): Boolean {
        val post = getPostById(postId)
        val author = post.author
        val authenticatedUser = authentication.principal as User
        return author == authenticatedUser
    }
}
