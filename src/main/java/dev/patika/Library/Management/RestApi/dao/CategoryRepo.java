package dev.patika.Library.Management.RestApi.dao;

import dev.patika.Library.Management.RestApi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
