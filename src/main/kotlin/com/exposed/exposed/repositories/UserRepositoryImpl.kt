package com.exposed.exposed.repositories



import com.exposed.exposed.Cities
import com.exposed.exposed.Users
import com.exposed.exposed.entities.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional


@Repository
@Transactional
class UserRepositoryImpl: UserRepository {


    override fun createTable() = SchemaUtils.create(Users)


    private fun toRow(user: User, idCity: Int): Users.(UpdateBuilder<*>) -> Unit = {
        it[id]=user.id
        it[firstName] = user.firstName
        it[lastName] = user.lastName

        it[birthDay]=user.birthDay
        it[cityId] = idCity

    }

    private fun fromRow(r: ResultRow) =
            User(r[Users.id],r[Users.firstName], r[Users.lastName],r[Users.birthDay], r[Users.cityId])

    override fun create(user: User): User {
        val saintPetersburgId = Cities.insert {
            it[name] = "St. Petersburg"
        } get Cities.id

        Users.insert(toRow(user, saintPetersburgId))
        return user
    }


    override fun find(id:Int){

        println("Manual join:")
        (Users innerJoin Cities).slice(Users.firstName, Cities.name).
                select {(Users.id.eq(id) /*or Users.firstName.eq(firstName)*/) and
                        Users.id.eq(id) and Users.cityId.eq(Cities.id)}.forEach {
            println("${it[Users.firstName]} lives in ${it[Cities.name]}")
        }


    }




    override fun findAll() = Users.selectAll().map { fromRow(it) }
    override fun deleteAll() = Users.deleteAll()
    override fun delete(firstName: String) = Users.deleteWhere { Users.firstName like firstName }

    override fun updateUser(id: Int, name: String) {
        Users.update({ Users.id eq id }) { it[firstName] = name }

    }

    override fun findByid(id: Int) =
            Users.select { Users.id.eq(id) }.firstOrNull()


}

