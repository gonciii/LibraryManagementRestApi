package dev.patika.Library.Management.RestApi.dao;

import dev.patika.Library.Management.RestApi.entities.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBorrowingRepo extends JpaRepository<BookBorrowing,Integer> {
}
