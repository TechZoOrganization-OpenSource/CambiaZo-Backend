package techzo.com.example.cambiazo.exchanges.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateExchangeCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.valueobjects.ExchangeStatus;
import techzo.com.example.cambiazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


/**
 * Represents Exchange in the Cambiazo system.
 *
 * @author CambiaZo - TechZo
 * @version 1.0
 *
 */

@Setter
@Getter
@Entity
public class Exchange extends AuditableAbstractAggregateRoot<Exchange> {

    @ManyToOne
    @JoinColumn(name = "product_own_id", nullable = false)
    @NotNull(message = "Product Own ID is mandatory")
    private Product productOwnId;

    @ManyToOne
    @JoinColumn(name = "product_change_id", nullable = false)
    @NotNull(message = "Product Change ID is mandatory")
    private Product productChangeId;

    @Embedded
    @Column(name = "status", nullable = false)
    @NotNull(message = "Status is mandatory")
    private ExchangeStatus status;

    @Getter
    private Long userOwnId;
    @Getter
    private Long userChangeId;

    public Exchange() {
    }

    public Exchange(CreateExchangeCommand command, Product productOwnId, Product productChangeId) {
        this.productOwnId = productOwnId;
        this.productChangeId = productChangeId;
        this.status = new ExchangeStatus(command.status());
        this.userOwnId = productOwnId.getUserId();
        this.userChangeId = productChangeId.getUserId();
    }

    public Long getExchangeId() {
        return getId();
    }

    public Long getProductOwnId() {
        return productOwnId.getId();
    }

    public Long getProductChangeId() {
        return productChangeId.getId();
    }

    public String getStatus() {
        return status.getExchangeStatus();
    }



}
