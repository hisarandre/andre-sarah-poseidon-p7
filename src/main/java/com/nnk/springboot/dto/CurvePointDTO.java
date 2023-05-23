package com.nnk.springboot.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CurvePointDTO {

    private Integer id;

    @NotNull(message = "Curve ID must not be null")
    @Min(value = 1, message = "Curve ID must be greater than or equal to 1")
    private Integer curveId;

    @NotNull(message = "Term must not be null")
    private Double term;

    @NotNull(message = "Value must not be null")
    private Double value;

}
