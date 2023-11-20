package com.ngola.psfotos.model;


import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "fkAlbum")
    private Album albumFk;
}
