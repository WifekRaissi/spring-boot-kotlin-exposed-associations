package com.exposed.exposed.repositories


import com.exposed.exposed.entities.User
import org.jetbrains.exposed.sql.ResultRow


interface UserRepository{


    fun createTable()
    fun create(user: User): User
    fun findAll(): List<User>
    fun deleteAll(): Int
    fun delete(firstName: String): Int
    fun updateUser(id: Int, firstName: String)
    fun findByid(id: Int): ResultRow?
    fun find(id: Int)
}