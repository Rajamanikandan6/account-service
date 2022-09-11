package com.maveric.accountservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import com.maveric.accountservice.constants.Type;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PUBLIC)
public class AccountDto {
    @Id
    private String _id;
    @Enumerated(EnumType.STRING)
    private Type type;
    @NotBlank(message = "Customer Id is mandatory")
    private String customerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Balance> balance;
}
