package scalereal.core.models.graphql

import io.micronaut.core.annotation.Introspected

@Introspected
class Book(val id: String, val name: String, val pageCount: Int, val author: Author)
