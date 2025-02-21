package com.game.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Score {

    @Id
    @GeneratedValue
    private Long id;

    private final String userName;

//    private final Integer wins;
//
//    private final Integer loses;

    private final Integer enteredNumber;

    private final Integer generatedNumber;

    private final Date attemptDateTime;

}
