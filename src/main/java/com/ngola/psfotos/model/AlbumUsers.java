package com.ngola.psfotos.model;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "albumFk")
    private Album albumFk;

    @Nonnull
    @ManyToOne
    @JoinColumn(name="userName")
    private User userFk;

    public AlbumUsers(Album album, User user){
        this.albumFk = album;
        this.userFk = user;
    }
}
