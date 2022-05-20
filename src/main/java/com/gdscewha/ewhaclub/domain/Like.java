package com.gdscewha.ewhaclub.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "clubId", nullable = false)
    private Club club;

    @Column(nullable = false)
    private Boolean deletedFlag;
}
