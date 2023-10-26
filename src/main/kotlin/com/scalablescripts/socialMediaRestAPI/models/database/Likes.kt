package com.scalablescripts.socialMediaRestAPI.models.database
import jakarta.persistence.*

@Entity
@Table(name = "likes")
data class Likes(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "likes_id_seq")
    @SequenceGenerator(name = "likes_id_seq", allocationSize = 1)
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var liker: User,

    @ManyToOne
    @JoinColumn(name = "posts_id")
    var post: Post,
)