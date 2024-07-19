package dev.patika.Library.Management.RestApi.dao;

import dev.patika.Library.Management.RestApi.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Integer> {
}
