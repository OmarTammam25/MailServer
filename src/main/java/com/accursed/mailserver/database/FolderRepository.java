package com.accursed.mailserver.database;

import com.accursed.mailserver.models.Folder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends CrudRepository<Folder,String> {
    Folder findByFolderName(String folderName);
    Folder findByUserIdAndFolderName(String userId, String folderName);
}
