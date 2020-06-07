package com.ambient.stories.data.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class PostWithUserData (
    @ColumnInfo(name = "post_id")
    var id : Long = 0L,

    @ColumnInfo(name = "user_id")
    var userId : Long = 0L,

    @ColumnInfo(name = "user_name")
    var userName : String = "",

    @ColumnInfo(name = "user_bio")
    var userBio : String = "",

    @ColumnInfo(name = "profile_image")
    var profileImageUri :String = "",

    @ColumnInfo(name = "post_heading")
    var postHeading : String,

    @ColumnInfo(name = "post_data")
    var postBody : String,

    @ColumnInfo(name = "post_image")
    var postImage : String,

    @ColumnInfo(name = "post_likes")
    var postLikes : Int,

    @ColumnInfo(name = "post_date")
    var postDate : String
)