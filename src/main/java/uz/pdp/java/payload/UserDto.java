package uz.pdp.java.payload;

import lombok.Data;
import uz.pdp.java.entity.StarBadge;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {


    @NotNull(message = "email must not null")
    private String email;

    @NotNull(message = "password must not null")
    private String password;

    private Integer starBadgeId;
}
