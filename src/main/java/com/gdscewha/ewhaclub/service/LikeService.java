package com.gdscewha.ewhaclub.service;


import com.gdscewha.ewhaclub.domain.Club;
import com.gdscewha.ewhaclub.domain.Like;
import com.gdscewha.ewhaclub.domain.User;
import com.gdscewha.ewhaclub.dto.MainPageClubDto;
import com.gdscewha.ewhaclub.exception.CustomException;
import com.gdscewha.ewhaclub.exception.ErrorCode;
import com.gdscewha.ewhaclub.repository.ClubRepository;
import com.gdscewha.ewhaclub.repository.LikeRepository;
import com.gdscewha.ewhaclub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final UserRepository userRepository;
    private final ClubRepository clubRepository;
    private final LikeRepository likeRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public String addLike(String userEmail, Long clubId) {
        List<User> userList = userRepository.findByEmail(userEmail);
        if(userList.size() < 1) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        User user = userList.get(0);


        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new CustomException(ErrorCode.CLUB_NOT_FOUND));

        Like insertLike = likeRepository.findByUserAndClub(user, club)
                .orElse(null);

        if (insertLike != null) {
            throw new CustomException(ErrorCode.LIKE_ALREADY_EXIST);
        }

        insertLike = Like.builder()
                .user(user)
                .club(club)
                .build();

        likeRepository.save(insertLike);

        return "SUCCESS ADD LIKE";
    }

    @Transactional
    public String deleteLike(String userEmail, Long clubId) {
        List<User> userList = userRepository.findByEmail(userEmail);
        if(userList.size() < 1) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        User user = userList.get(0);


        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new CustomException(ErrorCode.CLUB_NOT_FOUND));

        Like removeLike = likeRepository.findByUserAndClub(user, club)
                .orElseThrow(() -> new CustomException(ErrorCode.LIKE_ALREADY_NOT_EXIST));

        likeRepository.delete(removeLike);

        return "SUCCESS DELETE LIKE";
    }

    public List<MainPageClubDto> getLikeList(String userEmail) {
        List<User> userList = userRepository.findByEmail(userEmail);
        if(userList.size() < 1) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        User user = userList.get(0);

        List<Like> likeList = likeRepository.findByUser(user);

        if (likeList.size() < 1)
            throw new CustomException(ErrorCode.LIKE_NOT_FOUND);

        List<MainPageClubDto> clubList = new ArrayList<>();
        for (Like like : likeList) {
            Club club = clubRepository.findById(like.getClub().getClubId())
                    .orElseThrow(() -> new CustomException(ErrorCode.CLUB_NOT_FOUND));
            MainPageClubDto dto = MainPageClubDto.toMainPageClubDto(club);

            clubList.add(dto);
        }
        return clubList;
    }

}
