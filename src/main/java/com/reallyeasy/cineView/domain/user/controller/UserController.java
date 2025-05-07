package com.reallyeasy.cineView.domain.user.controller;

import com.reallyeasy.cineView.domain.user.dto.request.UserCreateRequest;
import com.reallyeasy.cineView.domain.user.dto.response.UserCreateResponse;
import com.reallyeasy.cineView.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Boolean> checkUserNameDuplicate(@PathVariable String userName) {
        return ResponseEntity.ok(userService.checkUserNameDuplicate(userName));
    }
}
