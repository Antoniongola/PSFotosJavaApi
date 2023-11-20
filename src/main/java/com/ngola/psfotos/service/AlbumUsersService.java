package com.ngola.psfotos.service;

import com.ngola.psfotos.model.Album;
import com.ngola.psfotos.model.AlbumUsers;
import com.ngola.psfotos.model.User;
import com.ngola.psfotos.repository.AlbumUsersRepository;
import com.ngola.psfotos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumUsersService implements IAlbumUsersService{

    @Autowired
    AlbumUsersRepository albumUsersRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean addAlbumUser(Album album, String username){
        Optional<User> user = this.userRepository.findById(username);
        if(user.isPresent()){
            User utilizador = user.get();
            this.albumUsersRepository.save(new AlbumUsers(album, utilizador));
            return true;
        }

        return false;
    }
    
    @Override
    public List<AlbumUsers> todosAlbumUsers() {
        return this.albumUsersRepository.findAll();
    }
}
