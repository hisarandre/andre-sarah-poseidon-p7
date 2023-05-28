package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id", unique = true, nullable = false)
    private Integer id;

    @NotBlank(message = "Moodys Rating is mandatory")
    @Column(name="moodys_rating")
    private String moodysRating;

    @NotBlank(message = "Sand P Rating is mandatory")
    @Column(name="sand_p_rating")
    private String sandPRating;

    @NotBlank(message = "Fitch Rating is mandatory")
    @Column(name="fitch_rating")
    private String fitchRating;

    @NotNull(message = "Order number must not be null")
    @Column(name="order_number")
    private Integer orderNumber;

    public Rating(String moodysRating, String sandPRating, String fitchRating, int i) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = i;
    }

}
