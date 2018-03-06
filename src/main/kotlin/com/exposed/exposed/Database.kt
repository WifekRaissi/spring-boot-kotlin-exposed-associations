package com.exposed.exposed


import org.jetbrains.exposed.sql.Table



object Users : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val firstName = text("firstName")
    val lastName = text("lastName")
    val birthDay = date("birthDay")
    val cityId = (integer("city_id") references Cities.id).nullable() // Column<Int?>
}

object Cities : Table() {
    val id = integer("id").autoIncrement().primaryKey() // Column<Int>
    val name = varchar("city_name", 50) // Column<String>
}

