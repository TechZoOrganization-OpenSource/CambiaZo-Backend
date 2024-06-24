package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.UpdateProductCommand;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.UpdateProductResource;

public class UpdateProductCommandFromResourceAssembler {

        public static UpdateProductCommand toCommandFromResource(Long productId, UpdateProductResource resource) {
            return new UpdateProductCommand(productId, resource.name(), resource.description(), resource.desiredObject(), resource.price(), resource.image(), resource.boost(), resource.available(), resource.productCategoryId(), resource.userId(), resource.districtId());
        }
}
