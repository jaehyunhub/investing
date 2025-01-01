package shareAlbum.shareAlbum.domain.album.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shareAlbum.shareAlbum.domain.album.dto.AlbumRegisterDto;
import shareAlbum.shareAlbum.domain.album.entity.Album;
import shareAlbum.shareAlbum.domain.album.service.AlbumService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping("/uploadAlbum")
    public ResponseEntity<String> uploadAlbum(@ModelAttribute AlbumRegisterDto albumRegisterDto
    ) throws IOException{
        try{
            System.out.println("albumRegisterDto = " + albumRegisterDto);
            //Security Coding (xssFilter, upload file제한 추가)
            albumService.uploadPicture(albumRegisterDto);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
