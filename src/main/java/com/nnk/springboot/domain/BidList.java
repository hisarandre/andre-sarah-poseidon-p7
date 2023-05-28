package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bidlist_id", nullable = false)
    private Integer bidListId;

    @Column(name = "account", nullable = false)
    private String account;

    @Column(name="type", nullable = false)
    private String type;

    @Column(name="bid_quantity")
    private Double bidQuantity;

    @Column(name="ask_quantity")
    private Double askQuantity;

    @Column(name="bid")
    private Double bid;

    @Column(name="ask")
    private Double ask;

    @Column(name="benchmark")
    private String benchmark;

    @Column(name="bid_list_date")
    private Timestamp bidListDate;

    @Column(name="commentary")
    private String commentary;

    @Column(name="security")
    private String security;

    @Column(name="status")
    private String status;

    @Column(name="trader")
    private String trader;

    @Column(name="book")
    private String book;

    @Column(name="creation_name")
    private String creationName;

    @Column(name="creation_date")
    private Timestamp creationDate;

    @Column(name="revision_name")
    private String revisionName;

    @Column(name="revision_date")
    private Timestamp revisionDate;

    @Column(name="deal_name")
    private String dealName;

    @Column(name="deal_type")
    private String dealType;

    @Column(name="source_list_id")
    private String sourceListId;

    @Column(name="side")
    private String side;

    //constructor
    public BidList(String accountTest, String typeTest, double v) {
        this.account = accountTest;
        this.type = typeTest;
        this.bidQuantity = v;
    }
}
