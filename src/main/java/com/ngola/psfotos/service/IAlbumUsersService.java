package com.ngola.psfotos.service;

import com.ngola.psfotos.model.Album;
import com.ngola.psfotos.model.AlbumUsers;

import java.util.List;

public interface IAlbumUsersService {
    public boolean addAlbumUser(Album album, String username);
    public List<AlbumUsers> todosAlbumUsers();
}
