package shareAlbum.shareAlbum.domain.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shareAlbum.shareAlbum.domain.group.entity.MyGroup;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MyGroupDto;

import java.util.List;

public interface MyGroupRepository extends JpaRepository<MyGroup,Long> {
    @Query("SELECT new shareAlbum.shareAlbum.domain.member.query.mainPage.MyGroupDto(m.id, gl.groupTitle,gl.id) " +
            "FROM MyGroup m " +
            "LEFT JOIN m.groupList gl " +
            "WHERE m.member.id = :id")
    List<MyGroupDto> findByMemberId(@Param("id") Long id);

}
