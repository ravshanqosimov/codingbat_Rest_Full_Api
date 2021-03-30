package uz.pdp.java.payload;


import lombok.Data;
import uz.pdp.java.entity.Language;

import javax.validation.constraints.NotNull;

@Data
public class StarBadgeDto {

    private Integer score;

    private Integer languageId;


}
