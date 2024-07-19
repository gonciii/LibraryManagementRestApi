package dev.patika.Library.Management.RestApi.api;


import dev.patika.Library.Management.RestApi.business.abstracts.IPublisherService;
import dev.patika.Library.Management.RestApi.core.config.modelMapper.IModelMapperService;
import dev.patika.Library.Management.RestApi.core.result.Result;
import dev.patika.Library.Management.RestApi.core.result.ResultData;
import dev.patika.Library.Management.RestApi.core.utilies.ResultHelper;
import dev.patika.Library.Management.RestApi.dto.request.publisher.PublisherSaveRequest;
import dev.patika.Library.Management.RestApi.dto.request.publisher.PublisherUpdateRequest;
import dev.patika.Library.Management.RestApi.dto.response.CursorResponse;
import dev.patika.Library.Management.RestApi.dto.response.bookBorrowing.BookBorrowingResponse;
import dev.patika.Library.Management.RestApi.dto.response.publisher.PublisherResponse;
import dev.patika.Library.Management.RestApi.entities.BookBorrowing;
import dev.patika.Library.Management.RestApi.entities.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publishers")

public class PublisherController {
    private final IPublisherService publisherService;
    private final IModelMapperService modelMapper;


    public PublisherController(IPublisherService publisherService, IModelMapperService modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }

    // SAVE
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<PublisherResponse> save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest) {
        // request ---> publisher
        Publisher savePublisher = this.modelMapper.forRequest().map(publisherSaveRequest, Publisher.class);
        this.publisherService.save(savePublisher);

        // publisher ---> response
        return ResultHelper.created(this.modelMapper.forResponse().map(savePublisher,PublisherResponse.class));

    }

    // GET
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> get(@PathVariable("id") int id) {
        Publisher publisher= this.publisherService.get(id);

        PublisherResponse publisherResponse = this.modelMapper.forResponse().map(publisher,PublisherResponse.class);
        return ResultHelper.success(publisherResponse);
    }


    // UPDATE
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> update(@Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest) {
        Publisher updatePublisher = this.modelMapper.forRequest().map(publisherUpdateRequest,Publisher.class);

        this.publisherService.update(updatePublisher);
        return ResultHelper.success(this.modelMapper.forResponse().map(updatePublisher,PublisherResponse.class));
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.publisherService.delete(id);
        return ResultHelper.ok();
    }

    // CURSOR
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<PublisherResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize
    ) {
        Page<Publisher> publisherPage = this.publisherService.cursor(page, pageSize);
        Page<PublisherResponse> publisherResponsePage = publisherPage
                .map(publisher -> this.modelMapper.forResponse().map(publisher, PublisherResponse.class));


        CursorResponse<PublisherResponse> cursor = new CursorResponse<>();
        cursor.setItems(publisherResponsePage.getContent());
        cursor.setPageNumber(publisherResponsePage.getNumber());
        cursor.setPageSize(publisherResponsePage.getSize());
        cursor.setTotalElement(publisherResponsePage.getTotalElements());

        return ResultHelper.success(cursor);
    }


}
