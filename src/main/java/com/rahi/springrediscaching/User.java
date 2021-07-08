package com.rahi.springrediscaching;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User implements Serializable {
    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_USER", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_GEN")
    private Long id;
    private String name;
    private long followers;

    public User( String name, int followers ) {
        this.name = name;
        this.followers = followers;
    }
}
