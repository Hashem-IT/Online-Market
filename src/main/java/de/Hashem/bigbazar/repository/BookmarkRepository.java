package de.Hashem.bigbazar.repository;

import de.Hashem.bigbazar.entity.Bookmark;
import org.springframework.data.repository.CrudRepository;

/*
* Klasse für BookmarkRepository
*/

public interface BookmarkRepository extends CrudRepository<Bookmark, Long> {
}
