package books;

import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class BookRepository {
    private List<Book> bookList = new ArrayList<>();

    public List<Book> findAll() {
        return bookList;
    }

    public Optional<Book> findById(int id) {
        return bookList.stream().filter(book -> book.getId() == id).findFirst();
    }

    public void delete(int id) {
        bookList = bookList.stream().filter(book -> book.getId() != id).collect(Collectors.toList());
    }

    public void add(Book book) {
        if (!findById(book.getId()).isPresent()) {
            bookList.add(book);
        }
    }

    public void update(Book book) {
        Optional<Book> bookOptional = findById(book.getId());
        if (bookOptional.isPresent()) {
            Book bookToUpdate = bookOptional.get();

            bookToUpdate.setDescription(book.getDescription());
            bookToUpdate.setTitle(book.getTitle());
        }
    }

}
