package com.ngola.psfotos.service;

import com.dropbox.core.DbxException;
import com.ngola.psfotos.model.Photo;

public interface IDropboxService {
    public void imprimirDadosDaConta() throws DbxException;
    public void imprimirFicheirosNaRaizDaNuvem() throws DbxException;
    public boolean criarNovaPasta(String nomeDaPasta);
    public boolean newDropBoxFolder(String nomeDoFolder);
    public boolean carregarImagem(Photo imagem) throws DbxException ;
}
