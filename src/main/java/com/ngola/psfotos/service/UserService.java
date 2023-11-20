package com.ngola.psfotos.service;

import com.ngola.psfotos.model.User;
import com.ngola.psfotos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean cadastrarUtilizador(User user){
        if(this.selecionarUtilizador(user.getUserName()) == null){
            this.userRepository.save(user);
            return true;
        }

        return false;
    }

    @Override
    public boolean login(String username, String password) {
        User user = this.selecionarUtilizador(username);
        if(user != null) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User selecionarUtilizador(String username) {
        Optional<User> user = this.userRepository.findById(username);
        if(user.isPresent())
            return user.get();
        return null;
    }
    @Override
    public List<User> selecionarTodos() {
        return this.userRepository.findAll();
    }

    public List <User> pesquisarUser(String username){
        return this.userRepository.findByUserNameContainingIgnoreCase(username);
    }
}
