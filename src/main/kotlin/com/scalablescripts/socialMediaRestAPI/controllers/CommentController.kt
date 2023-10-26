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
@RequestMapping("/api/comments/{postId}")
class CommentController() {

}
