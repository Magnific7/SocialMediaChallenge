package com.scalablescripts.socialMediaRestAPI.controllers

import org.springframework.security.access.prepost.PreAuthorize
import com.scalablescripts.socialMediaRestAPI.configs.toUser
import com.scalablescripts.socialMediaRestAPI.dtos.CommentDto
import com.scalablescripts.socialMediaRestAPI.models.database.Comment
import com.scalablescripts.socialMediaRestAPI.services.CommentService
import com.scalablescripts.socialMediaRestAPI.services.PostService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/comment")
class CommentController(
    private val commentService: CommentService,
    private val postService: PostService
) {
    @Operation(
        summary = "Create a new comment",
        description = "Create a new comment on a post with the provided data",
        tags = ["Comments"]
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "201",
            description = "Comment created successfully",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Comment::class))]
        ),
        ApiResponse(responseCode = "400", description = "Bad request"),
        ApiResponse(responseCode = "401", description = "Unauthorized")
    ])
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/{postId}")
    fun createComment(
        @PathVariable postId: Long,
        @RequestBody commentRequestDto: CommentDto,
        authentication: Authentication
    ): Comment {
        val post = postService.getPostById(postId)
        val author = authentication.toUser()

        val comment = Comment(
            comment = commentRequestDto.comment ?: "",
            commenter = author,
            post = post
        )

        return commentService.createComment(comment)
    }

    @Operation(
        summary = "Update a comment",
        description = "Update a comment with the provided data",
        tags = ["Comments"]
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Comment updated successfully",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Comment::class))]
        ),
        ApiResponse(responseCode = "400", description = "Bad request"),
        ApiResponse(responseCode = "401", description = "Unauthorized"),
        ApiResponse(responseCode = "404", description = "Comment not found"),
        ApiResponse(responseCode = "403", description = "Forbidden")
    ])
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{commentId}")
    fun updateComment(@PathVariable commentId: Long, @RequestBody updatedComment: CommentDto): Comment {

        return commentService.updateComment(commentId, updatedComment)
    }

    @Operation(
        summary = "Delete a comment",
        description = "Delete a comment by its unique ID",
        tags = ["Comments"]
    )
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Comment deleted successfully"),
        ApiResponse(responseCode = "404", description = "Comment not found"),
        ApiResponse(responseCode = "403", description = "Forbidden")
    ])
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable commentId: Long) {
        commentService.deleteComment(commentId)
    }

    @Operation(
        summary = "Get all comments",
        description = "Retrieve all comments ",
        tags = ["Comments"]
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Comments retrieved successfully",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Comment::class))]
        ),
        ApiResponse(responseCode = "404", description = "No comments found for the post")
    ])
    @GetMapping()
    fun getAllComments(): List<Comment> {
        return commentService.getAllComments()
    }

    @Operation(
        summary = "Get a comment by ID ",
        description = "Retrieve a single comment by its unique ID ",
        tags = ["Comments"]
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Comment retrieved successfully",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Comment::class))]
        ),
        ApiResponse(responseCode = "404", description = "Comment not found")
    ])
    @GetMapping("/{commentId}")
    fun getCommentById(@PathVariable commentId: Long): Comment {
        return commentService.getCommentById(commentId)
    }
}
