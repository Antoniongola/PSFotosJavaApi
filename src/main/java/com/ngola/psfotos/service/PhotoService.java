package com.ngola.psfotos.service;

import com.dropbox.core.DbxException;
import com.ngola.psfotos.model.Photo;
import com.ngola.psfotos.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService implements IPhotoService{

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private DropboxService dropboxService;

    @Override
    public Photo addPhoto(Photo photo) throws DbxException {
        if(this.dropboxService.carregarImagem(photo))
            return this.photoRepository.save(photo);

        return null;
    }

    @Override
    public Photo selectPhoto(long id) {
        if(this.photoRepository.findById(id).isPresent())
            return this.photoRepository.findById(id).get();

        return null;
    }

    @Override
    public List<Photo> selectAllPhotos() {
        return this.photoRepository.findAll();
    }

    public void deletePhoto(long id){
        this.photoRepository.deleteById(id);
    }
}
