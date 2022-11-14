package scalereal.graphql

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import jakarta.inject.Singleton
import scalereal.core.models.graphql.Author
import scalereal.core.models.graphql.Book

@Singleton
class GraphQLDataFetchers(private val dbRepository: DbRepository) {

    fun bookByIdDataFetcher(): DataFetcher<Book> {
        return DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
            val bookId: String = dataFetchingEnvironment.getArgument("id")
            dbRepository.findAllBooks()
                .firstOrNull { book: Book -> (book.id == bookId) }
        }
    }

    fun authorDataFetcher(): DataFetcher<Author> {
        return DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
            val book: Book = dataFetchingEnvironment.getSource()
            val authorBook: Author = book.author
            dbRepository.findAllAuthors()
                .firstOrNull { author: Author -> (author.id == authorBook.id) }
        }
    }
}
