package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
@Table(name = "trade")
public class Trade {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Column(name="trade_id", unique = true, nullable = false)
        private Integer tradeId;

        @Column(name="account")
        private String account;

        @Column(name="type")
        private String type;

        @Column(name="buy_quantity")
        private Double buyQuantity;

        @Column(name="sell_quantity")
        private Double sellQuantity;

        @Column(name="buy_price")
        private Double buyPrice;

        @Column(name="sell_price")
        private Double sellPrice;

        @Column(name="trade_date")
        private Date tradeDate;

        @Column(name="security")
        private String security;

        @Column(name="status")
        private String status;

        @Column(name="trader")
        private String trader;

        @Column(name="benchmark")
        private String benchmark;

        @Column(name="book")
        private String book;

        @Column(name="creation_name")
        private String creationName;

        @Column(name="creation_date")
        private Date creationDate;

        @Column(name="revision_name")
        private String revisionName;

        @Column(name="revision_date")
        private Date revisionDate;

        @Column(name="deal_name")
        private String dealName;

        @Column(name="deal_type")
        private String dealType;

        @Column(name="source_list_id")
        private String sourceListId;

        @Column(name="side")
        private String side;

        //constructor
        public Trade(String account, String type) {
                this.account = account;
                this.type = type;
        }

}
