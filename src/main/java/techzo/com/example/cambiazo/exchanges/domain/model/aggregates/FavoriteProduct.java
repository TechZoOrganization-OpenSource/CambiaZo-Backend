package techzo.com.example.cambiazo.exchanges.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
public class FavoriteProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id" ,nullable = false)
    @NotNull(message = "Product ID is mandatory")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User ID is mandatory")
    private User userId;

    public FavoriteProduct() {
    }

    public FavoriteProduct(Product product, User userId) {
        this.product = product;
        this.userId = userId;
    }


    public Long getProductId() {
        return product.getId();
    }

    public Long getUserId() {
        return userId.getId();
    }
}
