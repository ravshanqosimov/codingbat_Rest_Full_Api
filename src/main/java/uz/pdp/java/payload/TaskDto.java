package uz.pdp.java.payload;

import lombok.Data;
import net.bytebuddy.asm.Advice;
import uz.pdp.java.entity.Category;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;


@Data
public class TaskDto {

    @NotNull(message = "name must not null")
    private String name;

    @NotNull(message = "text must not null")
    private String text;
    private String examples;

    private String solution;
    private Integer categoryId;
    private boolean hasStar;
}
