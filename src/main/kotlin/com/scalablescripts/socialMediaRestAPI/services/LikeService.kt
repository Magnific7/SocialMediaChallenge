package com.scalablescripts.socialMediaRestAPI.services
import com.scalablescripts.socialMediaRestAPI.dtos.ApiException
import com.scalablescripts.socialMediaRestAPI.models.database.Comment
import com.scalablescripts.socialMediaRestAPI.models.database.Like
import com.scalablescripts.socialMediaRestAPI.models.database.Post
import com.scalablescripts.socialMediaRestAPI.models.database.User
import com.scalablescripts.socialMediaRestAPI.repositories.LikeRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.data.repository.CrudRepository
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
    fun findLikeByPostAndLiker(post: Post, liker: User): Like? =
        likeRepo.findByPostAndLiker(post, liker)

    fun deleteLike(likeId: Long) {
        likeRepo.deleteById(likeId)
    }

}
