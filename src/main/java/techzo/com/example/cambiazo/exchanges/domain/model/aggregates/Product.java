package techzo.com.example.cambiazo.exchanges.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateProductCommand;
import techzo.com.example.cambiazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


/**
 * Represents a Product in the exchange system.
 *
 * @author CambiaZo - TechZo
 * @version 1.0
 *
 */

@Setter
@Getter
@Entity
public class Product extends AuditableAbstractAggregateRoot<Product>{

    @Column(nullable = false)
    @NotNull(message = "Name is required")
    @Getter
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotNull(message = "Description is required")
    @Getter
    private String description;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Getter
    @NotNull
    private String desiredObject;

    @Column(nullable = false)
    @NotNull(message = "Price is required")
    @Getter
    private Double price;

    @Column(nullable = false)
    @NotNull(message = "Image is required")
    @Getter
    private String image;

    @Column(nullable = false)
    @NotNull(message = "Boost is required")
    @Getter
    private Boolean boost;

    @Column(nullable = false)
    @NotNull(message = "Available is required")
    @Getter
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "product_category_id", nullable = false)
    @NotNull(message = "Product Category ID is mandatory")
    private ProductCategory productCategoryId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User ID is mandatory")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "District_id", nullable = false)
    @NotNull(message = "District ID is mandatory")
    private District districtId;

    public Product() {
    }

    public Product(CreateProductCommand command, ProductCategory productCategory, User user, District district) {
        this.name = command.name();
        this.description = command.description();
        this.desiredObject = command.desiredObject();
        this.price = command.price();
        this.image = command.image();
        this.boost = command.boost();
        this.available = command.available();
        this.productCategoryId = productCategory;
        this.userId = user;
        this.districtId = district;
    }

    public Product updateInformation(String name, String description, String desiredObject, Double price, String image, Boolean boost, Boolean available, ProductCategory productCategory, User user, District district) {
        this.name = name;
        this.description = description;
        this.desiredObject = desiredObject;
        this.price = price;
        this.image = image;
        this.boost = boost;
        this.available = available;
        this.productCategoryId = productCategory;
        this.userId = user;
        this.districtId = district;
        return this;
    }

    public Long getProductCategoryId() {
        return productCategoryId.getId();
    }

    public Long getUserId() {
        return userId.getId();
    }

    public Long getDistrictId() {
        return districtId.getId();
    }


}
