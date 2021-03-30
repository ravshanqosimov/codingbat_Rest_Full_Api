package uz.pdp.java.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.java.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Task extends AbsEntity {


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String text;
    @Column
    private String examples;


    @Column
    private String solution;

    @ManyToOne
    private Category category;

    @Column
    private boolean hasStar;
}
