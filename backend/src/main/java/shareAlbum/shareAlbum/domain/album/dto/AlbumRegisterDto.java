package shareAlbum.shareAlbum.domain.album.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
@AllArgsConstructor
public class AlbumRegisterDto {
    private String content;
    private String loginId;
    //private String createby;
    private MultipartFile image;
    private Long groupId;

}
