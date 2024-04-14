package com.questionpro.grocery.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderRequestDTO {
    private Long itemId;
    private Long quantity;

}
