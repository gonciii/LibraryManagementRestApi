package dev.patika.Library.Management.RestApi.business.abstracts;

import dev.patika.Library.Management.RestApi.entities.BookBorrowing;
import dev.patika.Library.Management.RestApi.entities.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {

    Category save(Category category);

    Category get(int id);

    Page<Category> cursor(int page, int pageSize);

    Category update(Category  category);

    boolean delete(int id);
}
