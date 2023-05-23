package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;

@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id", unique = true, nullable = false)
    private Integer id;

    @Column(name="curve_id")
    @Min(value = 1, message = "Curve ID must be greater than or equal to 1")
    private Integer curveId;

    @Column(name="as_of_date")
    private Timestamp asOfDate;

    @Column(name="term")
    @NotNull(message = "Term must not be null")
    private Double term;

    @Column(name="value")
    @NotNull(message = "Value must not be null")
    private Double value;

    @Column(name="creation_date")
    private Timestamp creationDate;

    public CurvePoint(Integer i, double v, double v1) {
    }}