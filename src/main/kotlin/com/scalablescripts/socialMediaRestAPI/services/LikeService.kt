package com.scalablescripts.socialMediaRestAPI.services
import com.scalablescripts.socialMediaRestAPI.models.database.Comment
import com.scalablescripts.socialMediaRestAPI.models.database.Like
import com.scalablescripts.socialMediaRestAPI.models.database.Post
import com.scalablescripts.socialMediaRestAPI.models.database.User
import com.scalablescripts.socialMediaRestAPI.repositories.LikeRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LikeService {
    @Autowired
    private lateinit var likeRepo: LikeRepo

    fun createLike(like: Like): Like {
        return likeRepo.save(like)
    }

    fun getLikeByPost(post: Post): List<Like> {
        return likeRepo.findByPost(post)
    }
}
