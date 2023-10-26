package com.scalablescripts.socialMediaRestAPI.controllers

import com.scalablescripts.socialMediaRestAPI.configs.toUser
import com.scalablescripts.socialMediaRestAPI.dtos.ApiException
import com.scalablescripts.socialMediaRestAPI.dtos.LoginDto
import com.scalablescripts.socialMediaRestAPI.dtos.LoginResponseDto
import com.scalablescripts.socialMediaRestAPI.dtos.PostDto
import com.scalablescripts.socialMediaRestAPI.models.database.Post
import com.scalablescripts.socialMediaRestAPI.models.database.User
import com.scalablescripts.socialMediaRestAPI.services.PostService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.security.core.annotation.AuthenticationPrincipal

@RestController
@RequestMapping("/api/posts")
class PostController(private val postService: PostService) {

    @PostMapping
    fun createPost(@RequestBody postRequestDto: PostDto, authentication: Authentication): Post {
        // You should add authentication and authorization logic here to associate the post with the authenticated user
        // For simplicity, we assume you have a user object available
        val author = authentication.toUser()
        val post = Post(
            title = postRequestDto.title ?: "",
            description = postRequestDto.description ?: "",
            author = author
        )
        return postService.createPost(post)
    }

    @GetMapping()
    fun getAllPosts(): List<Post> {
        return postService.getAllPosts()
    }

    @GetMapping("/{postId}")
    fun getPostById(@PathVariable postId: Long): Post {
        return postService.getPostById(postId)
    }

    @PutMapping("/{postId}")
    fun updatePost(@PathVariable postId: Long, @RequestBody updatedPost: PostDto): Post {

        return postService.updatePost(postId, updatedPost)
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long) {
        postService.deletePost(postId)
    }
}
