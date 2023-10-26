package com.scalablescripts.socialMediaRestAPI.models.database
import jakarta.persistence.*

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
    @JoinColumn(name = "user_id") // Specify the name of the foreign key column
    var author: User,

    @OneToMany(mappedBy = "post")
    var comments: List<Comment> = emptyList(),

    @OneToMany(mappedBy = "post")
    var likes: List<Likes> = emptyList()

)
