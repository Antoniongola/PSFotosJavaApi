package com.ngola.psfotos.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Albuns")

public class Album {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column
    private long id;
    @Column
    private String nome;
}