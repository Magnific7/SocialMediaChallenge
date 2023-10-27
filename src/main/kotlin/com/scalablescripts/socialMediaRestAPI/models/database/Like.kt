package com.scalablescripts.socialMediaRestAPI.models.database
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.util.*

@Entity
@Table(name = "post_like")
data class Like(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "like_id_seq")
    @SequenceGenerator(name = "like_id_seq", allocationSize = 1)
    var id: Long = 0,

    @Column
    var liked: Boolean = false,

    @ManyToOne
    var liker: User = User(),

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id")
    var post: Post,

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    var createdAt: Date? = null,
)