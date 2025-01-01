package shareAlbum.shareAlbum.domain.album.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shareAlbum.shareAlbum.domain.album.entity.Album;

public interface AlbumRepository extends JpaRepository<Album,Long> {
}
