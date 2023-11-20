package com.ngola.psfotos.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name="Utilizadores")
public class User {
    @Column
    private String nome;
    @Id
    @Column
    private String userName;
    @Column
    private String password;
}
