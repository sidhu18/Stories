package com.ambient.stories.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserData(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    var userId : Long = 0L,
    @ColumnInfo(name = "user_name")
    var userName : String = "",
    @ColumnInfo(name = "user_bio")
    var userBio : String = "",
    @ColumnInfo(name = "post_count")
    var postCount : Int = -1,
    @ColumnInfo(name = "followers_count")
    var followersCount : Int = -1,
    @ColumnInfo(name = "following_count")
    var following_count : Int = -1,
    @ColumnInfo(name = "profile_image")
    var profileImageUri :String = ""
//    @ColumnInfo(name = "join_date")
//    var joinDate : Long = System.currentTimeMillis()
)

fun List<UserData>.asDomainModel() : List<UserData>{
    return map {
        UserData(
            userId = it.userId,
            userName = it.userName,
            userBio = it.userBio,
            postCount = it.postCount,
            followersCount = it.followersCount,
            following_count = it.following_count,
            profileImageUri = it.profileImageUri
//            joinDate = it.joinDate
        )
    }
}