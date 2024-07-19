package dev.patika.Library.Management.RestApi.dao;


import dev.patika.Library.Management.RestApi.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book,Integer> {
}
