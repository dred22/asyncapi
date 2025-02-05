package com.magomed.a.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Entity
@Builder
@Table(name = "articles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    int articleId;
    @Column(name = "name", unique = true)
    String name;
    @Column(name = "weight")
    int weight;

    @ManyToOne
    @JoinColumn(name = "order_id")
    OrderEntity order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleEntity article = (ArticleEntity) o;
        return articleId == article.articleId && weight == article.weight && Objects.equals(name, article.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, name, weight);
    }

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "articleId=" + articleId +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", order=" + order.orderId +
                '}';
    }
}
