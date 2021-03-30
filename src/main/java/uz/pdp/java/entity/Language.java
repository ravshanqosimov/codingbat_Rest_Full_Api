package uz.pdp.java.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.java.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Language extends AbsEntity {

    @Column(nullable = false,unique = true)
    private String name;

    @Column
    private String description;
}
