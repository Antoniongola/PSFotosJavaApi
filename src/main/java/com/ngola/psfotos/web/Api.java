package com.ngola.psfotos.web;

import com.dropbox.core.DbxException;
import com.ngola.psfotos.model.*;
import com.ngola.psfotos.service.AlbumService;
import com.ngola.psfotos.service.AlbumUsersService;
import com.ngola.psfotos.service.PhotoService;
import com.ngola.psfotos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@RestController()
@RequestMapping(path = "/api", produces = {"application/json", "text/html"})
public class Api {
    @Autowired
    private UserService userService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private AlbumUsersService albumUsersService;

    @PostMapping(path = "/login")
    public boolean login(@RequestBody LoginDto user){
        return this.userService.login(user.getUsername(), user.getPassword());
    }

    @PostMapping(path = "/signup")
    public boolean criarConta(@RequestBody User user){
        return userService.cadastrarUtilizador(user);
    }

    @GetMapping(path="/users/all")
    public List<User> selecionarTodos(){
        return this.userService.selecionarTodos();
    }

    @GetMapping(path="/users/{username}")
    public User selecionarUtilizador(@PathVariable(name = "username") String username){
        return this.userService.selecionarUtilizador(username);
    }

    @PostMapping(path = "/users/{username}/albums/create")
    public boolean criarAlbum(@RequestBody Album album, @PathVariable String username){
        return this.albumService.criarAlbum(album, username);
    }

    @PostMapping(path="/users/{username}/albums/{albumId}/photos/add")
    public Photo adicionarFoto(@RequestBody PhotoDto photoDto, @PathVariable long albumId, String uri) throws DbxException {
        Album album = this.albumService.verAlbum(albumId);
        Photo photo = new Photo();
        photo.setNome(photoDto.getNomeFoto());
        photo.setAlbumFk(album);
        return photo;
    }

    @PostMapping(path = "/users/{username}/albums/{albumId}/participants/add")
    public boolean adicionarParticipanteAoAlbum(@PathVariable long albumId, @RequestBody AlbumUsersDto user){
        Album album = this.albumService.verAlbum(albumId);
        System.out.println(album);
        return this.albumUsersService.addAlbumUser(album, user.getUsername());
    }

    @GetMapping(path="/users/{username}/albums/{albumId}/participants")
    public List <User> participantesDeUmAlbum(@PathVariable long albumId){
        int i = 0;

        for (AlbumUsers albumUser: this.albumUsersService.todosAlbumUsers())
            if(albumUser.getAlbumFk().getId()==albumId)
                i++;

        User vetorDeUsers[] = new User[i];
        i = 0;

        for (AlbumUsers albumUser: this.albumUsersService.todosAlbumUsers()){
            if(albumUser.getAlbumFk().getId()==albumId){
                User novoUser = User.builder()
                        .userName(albumUser.getUserFk().getUserName())
                        .password(albumUser.getUserFk().getPassword())
                        .nome(albumUser.getUserFk().getNome())
                        .build();

                vetorDeUsers[i] = novoUser;
                i++;
            }
        }

        List<User> usersDoAlbum = Arrays.asList(vetorDeUsers);

        return usersDoAlbum;
    }

    @GetMapping(path = "/albums/users/")
    public List <AlbumUsers> todosAlbumUsers(){
        return this.albumUsersService.todosAlbumUsers();
    }

    @GetMapping(path="/users/{username}/albums/photos/all")
    public List<Photo> listAllPhotos(){
        return this.photoService.selectAllPhotos();
    }

    @GetMapping(path="/users/{username}/albums/{albumId}/photos/all")
    public List<Photo> listPhotosFromAlbum (@PathVariable long albumId){
        int i = 0;
        if(this.photoService.selectAllPhotos()!=null){
            for(Photo photo : this.photoService.selectAllPhotos())
                if(photo.getAlbumFk().getId() == albumId)
                    i++;

            Photo[] photoArray = new Photo[i];
            i = 0;
            for(Photo photo : this.photoService.selectAllPhotos())
                if(photo.getAlbumFk().getId() == albumId){
                    photoArray[i] = photo;
                    i++;
                }

            return Arrays.asList(photoArray);
        }

        return null;
    }

    @GetMapping(path = "/albums/all")
    public List<Album> todosAlbums(){
        return this.albumService.listAll();
    }

    @GetMapping(path = "users/{username}/albums/myalbums")
    public List<Album> listarAlbums(@PathVariable String username){
        int i=0;
        for(AlbumUsers albumUsers : this.albumUsersService.todosAlbumUsers())
            if(albumUsers.getUserFk().getUserName().equals(username))
                i++;

        Album albums [] = new Album[i];
        i = 0;
        for(AlbumUsers albumUsers : this.albumUsersService.todosAlbumUsers()){
            if(albumUsers.getUserFk().getUserName().equals(username)){
                albums[i] = albumUsers.getAlbumFk();
                i++;
            }
        }

        return Arrays.asList(albums);
    }

    @GetMapping(path = "/users/{username}/albums/newest")
    public long testeID(){
        return this.albumService.novoAlbum();
    }

    @PostMapping(path = "/users/{username}/search")
    public List<User> pesquisarUsers(@PathVariable String username, @RequestBody PesquisaDto user){
        List<User> userList = this.selecionarTodos();

        int i =  0;
        for(User utilizador : userList){
            if(utilizador.getUserName().contains(user.getNomeUser()) && !utilizador.getUserName().equalsIgnoreCase(username))
                i++;
        }
        User lista[] = new User[i];

        i=0;
        for(User utilizador : userList){
            if(utilizador.getUserName().contains(user.getNomeUser()) && !utilizador.getUserName().equalsIgnoreCase(username)) {
                lista[i] = utilizador;
                i++;
            }
        }

        return Arrays.asList(lista);
    }
}