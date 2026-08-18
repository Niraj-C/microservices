package com.nitech.cards.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cards extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    private String mobileNumber;

    private String cardNumber;

    private String cardType;

    private int totalLimit;

    private int amountUsed;

    private int availableAmount;



    @PrePersist
    public void prePersist() {
        this.setCreatedAt(LocalDateTime.now());

        if (this.getCreatedBy() == null) {
            this.setCreatedBy("SYSTEM");
        }
    }

}
