package uz.pdp.java.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.java.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Answer extends AbsEntity {



    private String body;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User user;

    private boolean isTrue;

}
