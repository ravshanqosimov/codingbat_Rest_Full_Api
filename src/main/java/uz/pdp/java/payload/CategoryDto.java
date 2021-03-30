package uz.pdp.java.payload;


import lombok.Data;
import uz.pdp.java.entity.Language;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDto {
    @NotNull(message = "name must not null")
    private String name;

    private String description;

    private Integer languageId;


}
