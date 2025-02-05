package com.magomed.a.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    int orderId;
    @Column(name = "amount")
    int amount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    List<ArticleEntity> articles;

    public void setArticles(List<ArticleEntity> articles){
        articles.forEach(article->article.setOrder(this));
        if (this.articles != null){
            this.articles.addAll(articles);
        } else {
            this.articles = articles;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return orderId == that.orderId && amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, amount);
    }
}
