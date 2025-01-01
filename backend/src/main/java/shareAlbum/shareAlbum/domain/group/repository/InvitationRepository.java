package shareAlbum.shareAlbum.domain.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shareAlbum.shareAlbum.domain.group.entity.Invitation;

import java.util.Optional;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    Optional<Invitation> findInvitationById(Long id);
}
