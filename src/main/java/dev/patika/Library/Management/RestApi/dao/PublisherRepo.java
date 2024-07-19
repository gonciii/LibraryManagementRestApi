package dev.patika.Library.Management.RestApi.dao;

import dev.patika.Library.Management.RestApi.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher,Integer> {
}
