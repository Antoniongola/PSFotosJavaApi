package com.ngola.psfotos.service;

import com.dropbox.core.DbxException;
import com.ngola.psfotos.model.Photo;

import java.io.IOException;
import java.util.List;

public interface IPhotoService {
    public Photo addPhoto(String uri, Photo photo) throws DbxException, IOException;
    public Photo selectPhoto(long id);
    public List<Photo> selectAllPhotos();
    public void deletePhoto(long id);

}
