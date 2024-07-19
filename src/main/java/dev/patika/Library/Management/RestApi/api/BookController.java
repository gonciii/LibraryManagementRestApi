package dev.patika.Library.Management.RestApi.api;


import dev.patika.Library.Management.RestApi.business.abstracts.IBookService;
import dev.patika.Library.Management.RestApi.core.config.modelMapper.IModelMapperService;
import dev.patika.Library.Management.RestApi.core.result.Result;
import dev.patika.Library.Management.RestApi.core.result.ResultData;
import dev.patika.Library.Management.RestApi.core.utilies.ResultHelper;
import dev.patika.Library.Management.RestApi.dto.request.book.BookSaveRequest;
import dev.patika.Library.Management.RestApi.dto.request.book.BookUpdateRequest;
import dev.patika.Library.Management.RestApi.dto.response.CursorResponse;
import dev.patika.Library.Management.RestApi.dto.response.book.BookResponse;
import dev.patika.Library.Management.RestApi.entities.Book;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/books")

public class BookController {
    private final IBookService bookService;
    private final IModelMapperService modelMapper;


    public BookController(IBookService bookService, IModelMapperService modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    // SAVE
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest) {
        // request ---> book
        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest,Book.class);
        this.bookService.save(saveBook);

        // book ---> response
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBook,BookResponse.class));

    }

    // GET
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> get(@PathVariable("id") int id) {
        Book book = this.bookService.get(id);

        BookResponse bookResponse = this.modelMapper.forResponse().map(book,BookResponse.class);
        return ResultHelper.success(bookResponse);
    }


    // UPDATE
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
        Book updateBook = this.modelMapper.forRequest().map(bookUpdateRequest,Book.class);

        this.bookService.update(updateBook);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateBook,BookResponse.class));
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.bookService.delete(id);
        return ResultHelper.ok();
    }

    // CURSOR
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize
    ) {
        Page<Book> bookPage = this.bookService.cursor(page, pageSize);
        Page<BookResponse> bookResponsePage = bookPage
                .map(book -> this.modelMapper.forResponse().map(book, BookResponse.class));


        CursorResponse<BookResponse> cursor = new CursorResponse<>();
        cursor.setItems(bookResponsePage.getContent());
        cursor.setPageNumber(bookResponsePage.getNumber());
        cursor.setPageSize(bookResponsePage.getSize());
        cursor.setTotalElement(bookResponsePage.getTotalElements());

        return ResultHelper.success(cursor);
    }


}
