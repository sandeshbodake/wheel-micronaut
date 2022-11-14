package scalereal.db.tables

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = long("id").autoIncrement()
    val firstName = varchar("first_name", length = 50)
    val lastName = varchar("last_name", length = 50)
    val userName = varchar("username", length = 50)
}
