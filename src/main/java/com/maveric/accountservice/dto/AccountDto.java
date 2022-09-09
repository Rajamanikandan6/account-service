package com.maveric.accountservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import com.maveric.accountservice.constants.Type;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PUBLIC)
public class AccountDto {
    @Id
    private String _id;
    @NotNull(message = "Type is mandatory - 'SAVINGS' or 'CURRENT'")
    private Type type;
    @NotBlank(message = "Customer Id is mandatory")
    private String customerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Balance balance;
}
