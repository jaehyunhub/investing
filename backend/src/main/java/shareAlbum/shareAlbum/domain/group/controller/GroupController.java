package shareAlbum.shareAlbum.domain.group.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import shareAlbum.shareAlbum.domain.group.dto.GroupAcceptionDto;
import shareAlbum.shareAlbum.domain.group.dto.GroupCreateDto;
import shareAlbum.shareAlbum.domain.group.dto.GroupInvitationDto;
import shareAlbum.shareAlbum.domain.group.entity.GroupList;
import shareAlbum.shareAlbum.domain.group.repository.GroupRepository;
import shareAlbum.shareAlbum.domain.group.repository.MyGroupRepository;
import shareAlbum.shareAlbum.domain.group.service.GroupService;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/createGroup")
    public ResponseEntity<String> createGroup(@Valid @RequestBody GroupCreateDto groupCreateDto, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return ResponseEntity.badRequest().body("입력을 제대로 해주세요");
            }
            groupService.createGroup(groupCreateDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("그룹 생성시 에러 발생");
        }
    }

    @PostMapping("/inviteGroup")
    public ResponseEntity<?> inviteGroup(@RequestBody GroupInvitationDto groupInvitationDto) {
        try{
            System.out.println("groupInvitationDto = " + groupInvitationDto);
            groupService.inviteGroup(groupInvitationDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/acceptGroupInvitation")
    public ResponseEntity<?> acceptGroup(@RequestBody GroupInvitationDto groupInvitationDto) {
        try{
            System.out.println("groupInvitationDto.toString() = " + groupInvitationDto.toString());
            groupService.acceptGroupInvitation(groupInvitationDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch(NoSuchElementException e){
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PostMapping("/rejectGroupInvitation ")
    public ResponseEntity<?> rejectGroup(@RequestBody GroupInvitationDto groupInvitationDto) {
        try{
            groupService.rejectGroupInvitation(groupInvitationDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }







}
