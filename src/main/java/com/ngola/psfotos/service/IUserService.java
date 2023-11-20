package com.ngola.psfotos.service;

import com.ngola.psfotos.model.User;

import java.util.List;

public interface IUserService {
    public boolean cadastrarUtilizador(User user);
    public boolean login(String username, String password);
    public User selecionarUtilizador(String username);
    public List<User> selecionarTodos();
    public List <User> pesquisarUser(String username);

}
