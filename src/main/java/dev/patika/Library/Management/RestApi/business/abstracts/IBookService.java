package dev.patika.Library.Management.RestApi.business.abstracts;


import dev.patika.Library.Management.RestApi.entities.Book;
import org.springframework.data.domain.Page;

public interface IBookService {

    Book save(Book book);

    Book get(int id);

    Page<Book> cursor(int page, int pageSize);

    Book update(Book book);

    boolean delete(int id);


}
