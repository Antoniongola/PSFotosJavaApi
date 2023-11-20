package com.ngola.psfotos;

import com.dropbox.core.DbxException;
import com.ngola.psfotos.model.Photo;
import com.ngola.psfotos.service.DropboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PsFotosApplication {

    public static void main(String[] args) throws DbxException {
        SpringApplication.run(PsFotosApplication.class, args);

        DropboxService dropbox = new DropboxService();
        dropbox.imprimirDadosDaConta();

        //dropbox.carregarImagem(new Photo());
        //dropbox.newDropBoxFolder("ola mundo");
    }
}
