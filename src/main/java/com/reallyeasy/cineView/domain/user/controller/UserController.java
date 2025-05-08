package com.reallyeasy.cineView.domain.user.controller;

import com.reallyeasy.cineView.domain.user.dto.request.UserCreateRequest;
import com.reallyeasy.cineView.domain.user.dto.request.UserUpdateRequest;
import com.reallyeasy.cineView.domain.user.dto.response.UserCreateResponse;
import com.reallyeasy.cineView.domain.user.dto.response.UserInfoResponse;
import com.reallyeasy.cineView.domain.user.dto.response.UserUpdateResponse;
import com.reallyeasy.cineView.domain.user.entity.User;
import com.reallyeasy.cineView.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserCreateResponse> join(@RequestBody UserCreateRequest request) {
        return ResponseEntity.ok(userService.join(request));
    }

    @GetMapping("/{username}/exists")
    public ResponseEntity<Boolean> checkUserNameDuplicate(@PathVariable("username") String userName) {
        return ResponseEntity.ok(userService.checkUserNameDuplicate(userName));
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoResponse> getUserInfo(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.getUserInfo(user.getId()));
    }

    @PatchMapping
    public ResponseEntity<UserUpdateResponse> update(@RequestBody UserUpdateRequest request, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.update(request, user.getId()));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@AuthenticationPrincipal User user) {
        userService.delete(user.getId());
        return ResponseEntity.ok().build();
    }
}
