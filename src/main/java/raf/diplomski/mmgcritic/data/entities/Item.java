package raf.diplomski.mmgcritic.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)
public class Item {
    @Id @GeneratedValue
    private Long id;

}
