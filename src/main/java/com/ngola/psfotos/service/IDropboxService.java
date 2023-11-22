package com.ngola.psfotos.service;

import com.dropbox.core.DbxException;
import com.ngola.psfotos.model.Photo;

import java.io.IOException;

public interface IDropboxService {
    public void imprimirDadosDaConta() throws DbxException;
    public void imprimirFicheirosNaRaizDaNuvem() throws DbxException;
    public boolean criarNovaPasta(String nomeDaPasta);
    public boolean newDropBoxFolder(String nomeDoFolder);
    public boolean guardarImagemNoPc(String uri, Photo foto) throws IOException;
    public boolean carregarImagem(String uri, Photo imagem) throws DbxException;
}
