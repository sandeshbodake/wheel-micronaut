package cps.api

import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Replaces
import org.jetbrains.exposed.sql.Database
import org.postgresql.ds.PGSimpleDataSource
import javax.sql.DataSource

@Context
@Replaces(DataSource::class)
class DevDataSource : PGSimpleDataSource() {
    init {
        Database.connect(
            "jdbc:postgresql://localhost/sample",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = ""
        )
    }
}
