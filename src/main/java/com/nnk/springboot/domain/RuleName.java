package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
@Table(name = "rulename")
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id", unique = true, nullable = false)
    private Integer id;

    @Column(name="name")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name="description")
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Column(name="json")
    @NotBlank(message = "Json is mandatory")
    private String json;

    @Column(name="template")
    @NotBlank(message = "Template is mandatory")
    private String template;

    @Column(name="sql_str")
    @NotBlank(message = "SqlStr is mandatory")
    private String sqlStr;

    @Column(name="sql_part")
    @NotBlank(message = "Sqlpart is mandatory")
    private String sqlPart;

    //constructor
    public RuleName(String ruleName, String description, String json, String template, String sql, String sqlPart) {
    this.name = ruleName;
    this.description = description;
    this.json = json;
    this.template = template;
    this.sqlStr = sql;
    this.sqlPart = sqlPart;
    }

}
