package com.scalablescripts.socialMediaRestAPI.models.database
import jakarta.persistence.*

@Entity
@Table(name = "comment")
data class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_seq")
    @SequenceGenerator(name = "post_id_seq", allocationSize = 1)
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var commenter: User,

    @ManyToOne
    @JoinColumn(name = "posts_id")
    var post: Post,

)

