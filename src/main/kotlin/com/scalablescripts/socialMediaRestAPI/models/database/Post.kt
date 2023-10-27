package com.scalablescripts.socialMediaRestAPI.models.database
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.util.*

@Entity
@Table(name = "posts")
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_seq")
    @SequenceGenerator(name = "post_id_seq", allocationSize = 1)
    var id: Long = 0,

    @Column
    var title: String = "",

    @Column
    var description: String = "",

    @ManyToOne
    var author: User,

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    var createdAt: Date? = null,

    )
