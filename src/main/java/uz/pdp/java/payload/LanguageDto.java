package uz.pdp.java.payload;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LanguageDto {

    @NotNull(message = "name must not null")
    private String name;
    private String description;


}
