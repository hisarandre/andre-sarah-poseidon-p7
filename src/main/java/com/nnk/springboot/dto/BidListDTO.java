package com.nnk.springboot.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BidListDTO {

    private Integer id;

    @NotBlank(message = "Account is mandatory")
    private String account;

    @NotBlank(message = "Type is mandatory")
    private String type;

    @Min(value = 1, message = "Bid quantity must be greater than or equal to 1")
    private Double bidQuantity;
}
