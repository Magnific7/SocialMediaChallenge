package com.scalablescripts.socialMediaRestAPI.controllers

import com.scalablescripts.socialMediaRestAPI.configs.toUser
import com.scalablescripts.socialMediaRestAPI.dtos.LikeDto
import com.scalablescripts.socialMediaRestAPI.models.database.Like
import com.scalablescripts.socialMediaRestAPI.models.database.Post
import com.scalablescripts.socialMediaRestAPI.services.LikeService
import com.scalablescripts.socialMediaRestAPI.services.PostService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/posts/{postId}/like")
class LikeController(
    private val likeService: LikeService,
    private val postService: PostService
) {
    @Operation(
        summary = "Create a new post",
        description = "Create a new post with the provided data",
        tags = ["Posts"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Post created successfully",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = Post::class))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request"),
            ApiResponse(responseCode = "401", description = "Unauthorized")
        ]
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    fun createLike(@PathVariable postId: Long, @RequestBody likeRequestDto: LikeDto, authentication: Authentication): Like {
        val liker = authentication.toUser()
        val post = postService.getPostById(postId)

        val like = Like(
            liked = likeRequestDto.liked,
            post = post,
            liker = liker
        )

        return likeService.createLike(like)

    }

}
