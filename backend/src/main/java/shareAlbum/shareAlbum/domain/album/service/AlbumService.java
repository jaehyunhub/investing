package shareAlbum.shareAlbum.domain.album.service;

import org.springframework.web.multipart.MultipartFile;
import shareAlbum.shareAlbum.domain.album.dto.AlbumRegisterDto;
import shareAlbum.shareAlbum.domain.album.entity.Album;

public interface AlbumService {

    //사진 업로드
    void uploadPicture(AlbumRegisterDto albumRegisterDto);
}
