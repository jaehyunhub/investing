package shareAlbum.shareAlbum.domain.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shareAlbum.shareAlbum.domain.group.entity.GroupList;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupList,Long> {

}
