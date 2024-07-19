package dev.patika.Library.Management.RestApi.dto.request.category;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoryUpdateRequest {

    @NotNull
    private int id;
    private String name;
    private String description;
    private List<Integer> bookId;
}
