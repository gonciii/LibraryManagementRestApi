package dev.patika.Library.Management.RestApi.api;


import dev.patika.Library.Management.RestApi.business.abstracts.IBookBorrowingService;

import dev.patika.Library.Management.RestApi.core.config.modelMapper.IModelMapperService;
import dev.patika.Library.Management.RestApi.core.result.Result;
import dev.patika.Library.Management.RestApi.core.result.ResultData;
import dev.patika.Library.Management.RestApi.core.utilies.ResultHelper;
import dev.patika.Library.Management.RestApi.dto.request.bookBorrowing.BookBorrowingSaveRequest;
import dev.patika.Library.Management.RestApi.dto.request.bookBorrowing.BookBorrowingUpdateRequest;
import dev.patika.Library.Management.RestApi.dto.response.CursorResponse;
import dev.patika.Library.Management.RestApi.dto.response.bookBorrowing.BookBorrowingResponse;
import dev.patika.Library.Management.RestApi.entities.BookBorrowing;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/v1/bookBorrowings")

public class BookBorrowingController {
    private final IBookBorrowingService bookBorrowingService;
    private final IModelMapperService modelMapper;

    // DI -IoS
    public BookBorrowingController(IBookBorrowingService bookBorrowingService, IModelMapperService modelMapper) {
        this.bookBorrowingService = bookBorrowingService;
        this.modelMapper = modelMapper;
    }

    // SAVE
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookBorrowingResponse> save(@Valid @RequestBody BookBorrowingSaveRequest bookBorrowingSaveRequest) {
        // request ---> bookBorrowing
        BookBorrowing saveBookBorrowing = this.modelMapper.forRequest().map(bookBorrowingSaveRequest,BookBorrowing.class);
        this.bookBorrowingService.save(saveBookBorrowing);

        // bookBorrowing ---> response
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBookBorrowing,BookBorrowingResponse.class));

    }

    // GET
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> get(@PathVariable("id") int id) {
        BookBorrowing bookBorrowing = this.bookBorrowingService.get(id);

        BookBorrowingResponse bookBorrowingResponse = this.modelMapper.forResponse().map(bookBorrowing,BookBorrowingResponse.class);
        return ResultHelper.success(bookBorrowingResponse);
    }


    // UPDATE
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> update(@Valid @RequestBody BookBorrowingUpdateRequest bookBorrowingUpdateRequest) {
        BookBorrowing updateBookBorrowing = this.modelMapper.forRequest().map(bookBorrowingUpdateRequest,BookBorrowing.class);

        this.bookBorrowingService.update(updateBookBorrowing);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateBookBorrowing,BookBorrowingResponse.class));
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.bookBorrowingService.delete(id);
        return ResultHelper.ok();
    }

    // CURSOR
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookBorrowingResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize
    ) {
        Page<BookBorrowing> bookBorrowingPage = this.bookBorrowingService.cursor(page, pageSize);
        Page<BookBorrowingResponse> bookBorrowingResponsePage = bookBorrowingPage
                .map(bookBorrowing -> this.modelMapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class));

        // CURSOR HATASI ALIRSAN BURAYA BAK !!!
        CursorResponse<BookBorrowingResponse> cursor = new CursorResponse<>();
        cursor.setItems(bookBorrowingResponsePage.getContent());
        cursor.setPageNumber(bookBorrowingResponsePage.getNumber());
        cursor.setPageSize(bookBorrowingResponsePage.getSize());
        cursor.setTotalElement(bookBorrowingResponsePage.getTotalElements());

        return ResultHelper.success(cursor);
    }
}
