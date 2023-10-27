package com.scalablescripts.socialMediaRestAPI.controllers

import com.scalablescripts.socialMediaRestAPI.configs.SwaggerConfig
import org.springframework.security.access.prepost.PreAuthorize
import com.scalablescripts.socialMediaRestAPI.configs.toUser
import com.scalablescripts.socialMediaRestAPI.dtos.ApiException
import com.scalablescripts.socialMediaRestAPI.dtos.LoginDto
import com.scalablescripts.socialMediaRestAPI.dtos.LoginResponseDto
import com.scalablescripts.socialMediaRestAPI.dtos.PostDto
import com.scalablescripts.socialMediaRestAPI.models.database.Post
import com.scalablescripts.socialMediaRestAPI.models.database.User
import com.scalablescripts.socialMediaRestAPI.services.PostService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.security.core.annotation.AuthenticationPrincipal

@RestController
@RequestMapping("/api/posts")

class PostController(private val postService: PostService) {
    @Operation(
        summary = "Create a new post",
        description = "Create a new post with the provided data",
        tags = ["Posts"]
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "201",
            description = "Post created successfully",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Post::class))]
        ),
        ApiResponse(responseCode = "400", description = "Bad request"),
        ApiResponse(responseCode = "401", description = "Unauthorized")
    ])
    @SecurityRequirement(name = SwaggerConfig.BEARER_AUTH)
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    fun createPost(@RequestBody postRequestDto: PostDto, authentication: Authentication): Post {

        val author = authentication.toUser()
        val post = Post(
            title = postRequestDto.title ?: "",
            description = postRequestDto.description ?: "",
            author = author
        )
        return postService.createPost(post)
    }

    @Operation(
        summary = "Get all posts",
        description = "Retrieve a list of all posts",
        tags = ["Posts"]
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Posts retrieved successfully",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Post::class))]
        ),
        ApiResponse(responseCode = "404", description = "No posts found")
    ])
    @SecurityRequirement(name = SwaggerConfig.BEARER_AUTH)
    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    fun getAllPosts(): List<Post> {
        return postService.getAllPosts()
    }

    @Operation(
        summary = "Get a post by ID",
        description = "Retrieve a post by its unique ID",
        tags = ["Posts"]
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Post retrieved successfully",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Post::class))]
        ),
        ApiResponse(responseCode = "404", description = "Post not found")
    ])
    @SecurityRequirement(name = SwaggerConfig.BEARER_AUTH)
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{postId}")
    fun getPostById(@PathVariable postId: Long): Post {
        return postService.getPostById(postId)
    }

    @Operation(
        summary = "Update a post by ID",
        description = "Update a post with the provided data by its unique ID",
        tags = ["Posts"]
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Post updated successfully",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Post::class))]
        ),
        ApiResponse(responseCode = "404", description = "Post not found")
    ])
    @SecurityRequirement(name = SwaggerConfig.BEARER_AUTH)
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{postId}")
    fun updatePost(@PathVariable postId: Long, @RequestBody updatedPost: PostDto): Post {

        return postService.updatePost(postId, updatedPost)
    }

    @Operation(
        summary = "Delete a post by ID",
        description = "Delete a post by its unique ID",
        tags = ["Posts"]
    )
    @ApiResponses(value = [

        ApiResponse(responseCode = "204", description = "Post deleted successfully"),
        ApiResponse(responseCode = "404", description = "Post not found"),
        ApiResponse(responseCode = "401", description = "Unauthorized")

    ])
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long) {
        postService.deletePost(postId)
    }
}