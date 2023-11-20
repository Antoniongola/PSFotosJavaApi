package com.ngola.psfotos.service;

import com.ngola.psfotos.model.Album;
import com.ngola.psfotos.model.AlbumDto;

import java.util.List;

public interface IAlbumService {

    public boolean criarAlbum(Album album, String username);
    public Album verAlbum(long id);

    public List<Album> listAll();
    //public AlbumDto joinAlbumUser();
    public long novoAlbum();
}
