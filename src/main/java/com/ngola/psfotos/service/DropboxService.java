package com.ngola.psfotos.service;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import com.ngola.psfotos.model.Photo;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class DropboxService implements IDropboxService {
    private static final String ACCESS_TOKEN = "sl.BqJ0Pxq3x_EgjwsPF3KepM1IlFxpDHgbs1YX7Y7oWiMOLOoUu5_LuMk2mYRpQR2tcIy_2MZNAVKAghpoBhlZl7DU0Hrb43MLr5jDgdY_fKgsl46UwIVBognHcs7SftaFBcYTrO4U0cqcWexWsNqrTyQ";
    DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/ngolapsfotos").build();
    DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

    public void imprimirDadosDaConta() throws DbxException {
        //tá aceder bem a minha conta cloud da dropbox
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());
    }

    @Override
    public void imprimirFicheirosNaRaizDaNuvem() throws DbxException {
        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }
    }

    @Override
    public boolean criarNovaPasta(String nomeDaPasta) {

        File pasta  = new File("C:/Users/Ngola/Desktop/Antonio/ISPTEC/4º ano/1º SEMESTRE/Aplicações Móveis/PSFotos/"+nomeDaPasta);

        pasta.mkdirs();
        boolean sucesso = pasta.mkdirs();

        if(sucesso)
            return true;

        return false;
    }

    public boolean newDropBoxFolder(String nomeDoFolder){
        try {
            this.client.files().createFolderV2("/"+nomeDoFolder);
            return true;
        } catch (DbxException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean carregarImagem(Photo imagem) throws DbxException {
        //aqui meto o caminho da imagem ou ficheiro (fonte)
        try (InputStream in = new FileInputStream("C:/Users/Ngola/Desktop/Antonio/ISPTEC/4º ano/1º SEMESTRE/Aplicações Móveis/PSFotos/"+imagem.getAlbumFk().getNome()+"/"+imagem.getNome())) {
            //aqui em baixo é pra meter a pasta da nuvem que irá conter o ficheiro acima mencionado.
            FileMetadata metadata = client.files().uploadBuilder("/"+imagem.getAlbumFk().getNome()+"/"+imagem.getNome())
                    .uploadAndFinish(in);
            System.out.println("ficheiro carregado com sucesso");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
