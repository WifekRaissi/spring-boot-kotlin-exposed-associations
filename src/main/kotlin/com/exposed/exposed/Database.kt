package com.exposed.exposed
import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val firstName = text("firstName")
    val lastName = text("lastName")
    val birthDay = date("birthDay")
    val cityId = (integer("cityId") references Cities.id).nullable() // Column<Int?>
}

object Cities : Table() {
    val id = integer("id").autoIncrement().primaryKey() // Column<Int>
    val name = varchar("name", 50) // Column<String>
}
