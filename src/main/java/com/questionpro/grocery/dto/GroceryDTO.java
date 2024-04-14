package com.questionpro.grocery.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroceryDTO {

    Long userId;
    List<OrderRequestDTO> orders;
}
