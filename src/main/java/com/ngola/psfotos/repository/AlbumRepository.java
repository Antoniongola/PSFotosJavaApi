package com.ngola.psfotos.repository;

import com.ngola.psfotos.model.Album;
import com.ngola.psfotos.model.AlbumDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>{

    @Query(value = "SELECT MAX(id) FROM Albuns", nativeQuery = true)
    public long novoId();
}
