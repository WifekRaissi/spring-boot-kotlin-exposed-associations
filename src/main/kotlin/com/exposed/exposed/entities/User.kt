package com.exposed.exposed.entities

import org.joda.time.DateTime


class User(
        val id:Int,
        val firstName  : String="",
        val lastName : String="",

        val birthDay: DateTime? ,
        cityId:Int?,
val age:Int

)