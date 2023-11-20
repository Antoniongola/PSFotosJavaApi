package com.ngola.psfotos.service;

import com.dropbox.core.DbxException;
import com.ngola.psfotos.model.Photo;

import java.util.List;

public interface IPhotoService {
    public Photo addPhoto(Photo photo) throws DbxException;
    public Photo selectPhoto(long id);
    public List<Photo> selectAllPhotos();
    public void deletePhoto(long id);

}
