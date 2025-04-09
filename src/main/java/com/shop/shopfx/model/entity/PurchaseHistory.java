package com.shop.shopfx.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class PurchaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private User appUser;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private GraphicsCard graphicsCard;
    @Temporal(TemporalType.DATE)
    private LocalDate sellDate;

    public PurchaseHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAppUser() {
        return appUser;
    }

    public void setAppUser(User appUser) {
        this.appUser = appUser;
    }

    public GraphicsCard getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(GraphicsCard graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }

    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseHistory that = (PurchaseHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(appUser, that.appUser) && Objects.equals(graphicsCard, that.graphicsCard) && Objects.equals(sellDate, that.sellDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appUser, graphicsCard, sellDate);
    }

    @Override
    public String toString() {
        return "PurchaseHistory{" +
                "id=" + id +
                ", appUser=" + appUser +
                ", graphicsCard=" + graphicsCard +
                ", sellDate=" + sellDate +
                '}';
    }
}