package com.gdscewha.ewhaclub.controller;

import com.gdscewha.ewhaclub.security.JwtTokenProvider;
import com.gdscewha.ewhaclub.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final JwtTokenProvider tokenProvider;

    @PostMapping(value = "/like/{clubId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> like(@RequestHeader("Authorization") String token,
                                  @PathVariable("clubId") Long clubId) {
        return new ResponseEntity<>(likeService.addLike(tokenProvider.getUserEmail(token), clubId), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/like/{clubId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> unlike(@RequestHeader("Authorization") String token,
                                  @PathVariable("clubId") Long clubId) {
        return new ResponseEntity<>(likeService.deleteLike(tokenProvider.getUserEmail(token), clubId), HttpStatus.OK);
    }

    @GetMapping(value = "/like")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getLikes(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(likeService.getLikeList(tokenProvider.getUserEmail(token)), HttpStatus.OK);
    }

}
