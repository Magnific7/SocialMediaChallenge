package com.scalablescripts.socialMediaRestAPI.models.database
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.util.*

@Entity
@Table(name = "comment")
data class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_seq")
    @SequenceGenerator(name = "comment_id_seq", allocationSize = 1)
    var id: Long = 0,

    @Column
    var comment: String = "",

    @ManyToOne
    var commenter: User = User(),

    @ManyToOne
    var post: Post,

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    var createdAt: Date? = null,
)

