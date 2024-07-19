package dev.patika.Library.Management.RestApi.api;


import dev.patika.Library.Management.RestApi.business.abstracts.ICategoryService;
import dev.patika.Library.Management.RestApi.core.config.modelMapper.IModelMapperService;
import dev.patika.Library.Management.RestApi.core.result.Result;
import dev.patika.Library.Management.RestApi.core.result.ResultData;
import dev.patika.Library.Management.RestApi.core.utilies.ResultHelper;

import dev.patika.Library.Management.RestApi.dto.request.category.CategorySaveRequest;
import dev.patika.Library.Management.RestApi.dto.request.category.CategoryUpdateRequest;
import dev.patika.Library.Management.RestApi.dto.response.CursorResponse;
import dev.patika.Library.Management.RestApi.dto.response.bookBorrowing.BookBorrowingResponse;
import dev.patika.Library.Management.RestApi.dto.response.category.CategoryResponse;
import dev.patika.Library.Management.RestApi.entities.BookBorrowing;
import dev.patika.Library.Management.RestApi.entities.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")

public class CategoryController {
    private final ICategoryService categoryService;
    private final IModelMapperService modelMapper;


    public CategoryController(ICategoryService categoryService, IModelMapperService modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    // SAVE
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest categorySaveRequest) {
        // request ---> category
        Category saveCategory = this.modelMapper.forRequest().map(categorySaveRequest,Category.class);
        this.categoryService.save(saveCategory);

        // book ---> response
        return ResultHelper.created(this.modelMapper.forResponse().map(saveCategory,CategoryResponse.class));

    }

    // GET
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> get(@PathVariable("id") int id) {
        Category category = this.categoryService.get(id);

        CategoryResponse categoryResponse = this.modelMapper.forResponse().map(category,CategoryResponse.class);
        return ResultHelper.success(categoryResponse);
    }


    // UPDATE
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        Category updateCategory = this.modelMapper.forRequest().map(categoryUpdateRequest,Category.class);

        this.categoryService.update(updateCategory);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateCategory,CategoryResponse.class));
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.categoryService.delete(id);
        return ResultHelper.ok();
    }

    // CURSOR
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize
    ) {
        Page<Category> categoryPage = this.categoryService.cursor(page, pageSize);
        Page<CategoryResponse> categoryResponsePage = categoryPage
                .map(category -> this.modelMapper.forResponse().map(category, CategoryResponse.class));

        // CURSOR HATASI ALIRSAN BURAYA BAK !!!
        CursorResponse<CategoryResponse> cursor = new CursorResponse<>();
        cursor.setItems(categoryResponsePage.getContent());
        cursor.setPageNumber(categoryResponsePage.getNumber());
        cursor.setPageSize(categoryResponsePage.getSize());
        cursor.setTotalElement(categoryResponsePage.getTotalElements());

        return ResultHelper.success(cursor);
    }



}
