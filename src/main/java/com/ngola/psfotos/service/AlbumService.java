package com.ngola.psfotos.service;

import com.ngola.psfotos.model.Album;
import com.ngola.psfotos.model.AlbumDto;
import com.ngola.psfotos.repository.AlbumRepository;
import com.ngola.psfotos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService implements IAlbumService{
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumUsersService albumUsersService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DropboxService dropboxService;

    @Override
    public boolean criarAlbum(Album album, String username) {
        if(this.albumRepository.findById(album.getId()).isPresent()) {
            return false;
        }else if((this.albumRepository.findById(album.getId()).isEmpty()) && userRepository.findById(username).isPresent()){
            if(this.dropboxService.criarNovaPasta(album.getNome())){
                this.dropboxService.newDropBoxFolder(album.getNome());
                this.albumRepository.save(album);
                this.albumUsersService.addAlbumUser(album, username);
                return true;
            }
            return false;
        }
        return false;
    }

    public List<Album> listAll(){
        return this.albumRepository.findAll();
    }

    @Override
    public long novoAlbum() {
        return this.albumRepository.novoId();
    }

    @Override
    public Album verAlbum(long id){
        Optional<Album> album = this.albumRepository.findById(id);
        return album.orElse(null);
    }
}