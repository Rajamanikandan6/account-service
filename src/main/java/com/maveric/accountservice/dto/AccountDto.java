package com.maveric.accountservice.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import com.maveric.accountservice.constants.Type;
import java.time.LocalDateTime;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PUBLIC)
//@Document(collection = "AccountDetails")
public class AccountDto {
    @Id
    private String id;
    private Type type;
    private String customerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //private Balance balance;
}
