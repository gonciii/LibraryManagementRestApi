package dev.patika.Library.Management.RestApi.api;

import dev.patika.Library.Management.RestApi.business.abstracts.IAuthorService;
import dev.patika.Library.Management.RestApi.core.config.modelMapper.IModelMapperService;
import dev.patika.Library.Management.RestApi.core.result.Result;
import dev.patika.Library.Management.RestApi.core.result.ResultData;
import dev.patika.Library.Management.RestApi.core.utilies.ResultHelper;
import dev.patika.Library.Management.RestApi.dto.request.author.AuthorSaveRequest;
import dev.patika.Library.Management.RestApi.dto.request.author.AuthorUpdateRequest;
import dev.patika.Library.Management.RestApi.dto.response.CursorResponse;
import dev.patika.Library.Management.RestApi.dto.response.author.AuthorResponse;
import dev.patika.Library.Management.RestApi.entities.Author;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {
    private final IAuthorService authorService;
    private final IModelMapperService modelMapper;


    public AuthorController(IAuthorService authorService, IModelMapperService modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    // SAVE
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest) {
        // request ---> author
        Author saveAuthor = this.modelMapper.forRequest().map(authorSaveRequest,Author.class);
        this.authorService.save(saveAuthor);

        // author ---> response
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAuthor,AuthorResponse.class));

    }

    // GET
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> get(@PathVariable("id") int id) {
        Author author = this.authorService.get(id);

        AuthorResponse authorResponse = this.modelMapper.forResponse().map(author,AuthorResponse.class);
        return ResultHelper.success(authorResponse);
    }


    // UPDATE
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest) {
        Author updateAuthor = this.modelMapper.forRequest().map(authorUpdateRequest,Author.class);

        this.authorService.update(updateAuthor);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAuthor,AuthorResponse.class));
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.authorService.delete(id);
        return ResultHelper.ok();
    }

    // CURSOR
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AuthorResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize
    ) {
        Page<Author> authorPage = this.authorService.cursor(page, pageSize);
        Page<AuthorResponse> authorResponsePage = authorPage
                .map(author -> this.modelMapper.forResponse().map(author, AuthorResponse.class));

        // CURSOR HATASI ALIRSAN BURAYA BAK !!!
        CursorResponse<AuthorResponse> cursor = new CursorResponse<>();
        cursor.setItems(authorResponsePage.getContent());
        cursor.setPageNumber(authorResponsePage.getNumber());
        cursor.setPageSize(authorResponsePage.getSize());
        cursor.setTotalElement(authorResponsePage.getTotalElements());

        return ResultHelper.success(cursor);
    }



}
