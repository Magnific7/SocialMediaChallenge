package com.scalablescripts.socialMediaRestAPI.controllers

import com.scalablescripts.socialMediaRestAPI.configs.toUser
import com.scalablescripts.socialMediaRestAPI.dtos.LikeDto
import com.scalablescripts.socialMediaRestAPI.models.database.Comment
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
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/like/")
class LikeController(
    private val likeService: LikeService,
    private val postService: PostService
) {
    @Operation(
        summary = "Create a like for a post",
        description = "Create a like for a post if it doesn't exist.",
        tags = ["Like"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Like created successfully",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = Like::class))]
            ),
            ApiResponse(responseCode = "401", description = "Unauthorized")
        ]
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{postId}")
    fun createLike(@PathVariable postId: Long, authentication: Authentication): ResponseEntity<out Any> {
        val liker = authentication.toUser()
        val post = postService.getPostById(postId)

        // Check if a like from the user on the post already exists.
        val existingLike = likeService.findLikeByPostAndLiker(post, liker)

        if (existingLike != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(existingLike)
        } else {
            // Create a new like with "liked" set to true.
            val newLike = Like(liked = true, post = post, liker = liker)
            val createdLike = likeService.createLike(newLike)
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLike)
        }
    }

    @Operation(
        summary = "Dislike a post",
        description = "This is used to dislike a post ",
        tags = ["Like"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Like deleted successfully"
            ),
            ApiResponse(responseCode = "401", description = "Unauthorized")
        ]
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{likeId}")
    fun deleteLike(@PathVariable likeId: Long) {
        likeService.deleteLike(likeId)
    }

}
