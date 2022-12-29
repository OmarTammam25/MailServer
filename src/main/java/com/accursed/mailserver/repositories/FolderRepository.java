package com.accursed.mailserver.repositories;

import com.accursed.mailserver.models.Folder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends CrudRepository<Folder,String> {
    Folder findByFolderName(String folderName);
}
