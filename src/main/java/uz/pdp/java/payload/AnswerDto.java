package uz.pdp.java.payload;

import lombok.Data;
import uz.pdp.java.entity.Task;
import uz.pdp.java.entity.User;

import javax.persistence.ManyToOne;

@Data
public class AnswerDto {

    private String body;

    private Integer taskId;

    private Integer userId;

    private boolean isTrue;



}
