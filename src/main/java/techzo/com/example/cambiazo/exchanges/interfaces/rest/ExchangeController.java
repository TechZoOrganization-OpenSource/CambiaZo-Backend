package techzo.com.example.cambiazo.exchanges.interfaces.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.*;
import techzo.com.example.cambiazo.exchanges.domain.services.ExchangeCommandService;
import techzo.com.example.cambiazo.exchanges.domain.services.ExchangeQueryService;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateExchangeResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.ExchangeResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.CreateExchangeCommandFromResourceAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.ExchangeResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/exchanges")
@Tag(name="Exchanges", description="Exchanges Management Endpoints")
public class ExchangeController {

    private final ExchangeCommandService exchangeCommandService;

    private final ExchangeQueryService exchangeQueryService;

    public ExchangeController(ExchangeCommandService exchangeCommandService, ExchangeQueryService exchangeQueryService){
        this.exchangeCommandService=exchangeCommandService;
        this.exchangeQueryService=exchangeQueryService;
    }

    @Operation(summary="Create a new Exchange", description="Create a new Exchange with the input data.")
    @PostMapping
    public ResponseEntity<ExchangeResource>createExchange(@RequestBody CreateExchangeResource resource){
        try {
            var createExchangeCommand= CreateExchangeCommandFromResourceAssembler.toCommandFromResource(resource);
            var exchange=exchangeCommandService.handle(createExchangeCommand);
            var exchangeResource= ExchangeResourceFromEntityAssembler.toResourceFromEntity(exchange.get());
            return ResponseEntity.status(CREATED).body(exchangeResource);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary="Get all Exchanges", description="Get all Exchanges.")
    @GetMapping
    public ResponseEntity<List<ExchangeResource>> getAllExchanges(){
        try {
            var getAllExchangesQuery=new GetAllExchangesQuery();
            var exchanges=exchangeQueryService.handle(getAllExchangesQuery);
            var exchangeResources=exchanges.stream().map(ExchangeResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(exchangeResources);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary="Get Exchange by id", description="Get Exchange by id.")
    @GetMapping("/{id}")
    public ResponseEntity<ExchangeResource> getExchangeById(@PathVariable Long id){
        try {
            var getExchangeByIdQuery=new GetExchangeByIdQuery(id);
            var exchange=exchangeQueryService.handle(getExchangeByIdQuery);
            var exchangeResource= ExchangeResourceFromEntityAssembler.toResourceFromEntity(exchange.get());
            return ResponseEntity.ok(exchangeResource);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }



    @Operation
    @GetMapping("/product-own/{productOwnId}")
    public ResponseEntity<List<ExchangeResource>> getAllExchangesByProductOwn(@PathVariable Long productOwnId){
        try {
            var getAllExchangesByProductOwnQuery=new GetAllExchangesByProductOwnQuery(productOwnId);
            var exchanges=exchangeQueryService.handle(getAllExchangesByProductOwnQuery);
            var exchangeResources=exchanges.stream().map(ExchangeResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(exchangeResources);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation
    @GetMapping("/product-change/{productChangeId}")
    public ResponseEntity<List<ExchangeResource>> getAllExchangesByProductChange(@PathVariable Long productChangeId){
        try {
            var getAllExchangesByProductChangeQuery=new GetAllExchangesByProductChangeQuery(productChangeId);
            var exchanges=exchangeQueryService.handle(getAllExchangesByProductChangeQuery);
            var exchangeResources=exchanges.stream().map(ExchangeResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(exchangeResources);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary="Get all Exchanges by User Own ID", description="Get all Exchanges where the product is owned by the specified user.")
    @GetMapping("/user-own/{userOwnId}")
    public ResponseEntity<List<ExchangeResource>> getAllExchangesByUserOwn(@PathVariable Long userOwnId) {
        try {
            var getAllExchangesByUserOwnQuery = new GetAllExchangesByUserOwnQuery(userOwnId);
            var exchanges = exchangeQueryService.handle(getAllExchangesByUserOwnQuery);
            var exchangeResources = exchanges.stream().map(ExchangeResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(exchangeResources);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary="Get all Exchanges by User Change ID", description="Get all Exchanges where the product is changed by the specified user.")
    @GetMapping("/user-change/{userChangeId}")
    public ResponseEntity<List<ExchangeResource>> getAllExchangesByUserChange(@PathVariable Long userChangeId) {
        try {
            var getAllExchangesByUserChangeQuery = new GetAllExchangesByUserChangeQuery(userChangeId);
            var exchanges = exchangeQueryService.handle(getAllExchangesByUserChangeQuery);
            var exchangeResources = exchanges.stream().map(ExchangeResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(exchangeResources);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

}
