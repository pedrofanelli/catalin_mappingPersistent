package com.example.demo.generator.model;

import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Item {

	@Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    @Version
    private long version;

    @NotNull
    @Size(
        min = 2,
        max = 255,
        message = "Name is required, maximum 255 characters."
    )
    private String name;

    @Future
    private Date auctionEnd;

    @Transient
    private Set<Bid> bids = new HashSet<>();

    public Long getId() { // Optional but useful
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(Date auctionEnd) {
        this.auctionEnd = auctionEnd;
    }

    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }
}
