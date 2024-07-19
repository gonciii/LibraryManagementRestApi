package dev.patika.Library.Management.RestApi.business.conceretes;

import dev.patika.Library.Management.RestApi.business.abstracts.IAuthorService;
import dev.patika.Library.Management.RestApi.core.exception.NotFoundException;
import dev.patika.Library.Management.RestApi.core.utilies.Msg;
import dev.patika.Library.Management.RestApi.dao.AuthorRepo;
import dev.patika.Library.Management.RestApi.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorManager implements IAuthorService {
    private final AuthorRepo authorRepo;

    // DI
    public AuthorManager(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public Author get(int id) {
        return this.authorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Author> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.authorRepo.findAll(pageable);
    }

    @Override
    public Author update(Author author) {
        this.get(author.getId()); // id control --> yoksa exception !
        return this.authorRepo.save(author);
    }

    @Override
    public boolean delete(int id) {
        Author author = this.get(id);
        this.authorRepo.delete(author);
        return true;
    }
}
