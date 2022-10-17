package com.mo16.customauthtoken.security.otp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int otp;
    private String username;
    private LocalDateTime created;
    private boolean active;
    private boolean isSent;

}
