package com.ngola.psfotos.repository;

import com.ngola.psfotos.model.AlbumUsers;
import com.ngola.psfotos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumUsersRepository extends JpaRepository<AlbumUsers, Long> {
}