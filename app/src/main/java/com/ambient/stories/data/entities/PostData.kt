package com.ambient.stories.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "post_table",foreignKeys = [ForeignKey(entity = UserData::class,
    parentColumns = arrayOf("user_id"),
    childColumns = arrayOf("user_id"),
    onDelete = ForeignKey.CASCADE)]
)
data class PostData (
    @PrimaryKey
    @ColumnInfo(name = "post_id")
    var id : Long = 0L,
    @ColumnInfo(name = "user_id")
    var userId : Long = 0L,
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