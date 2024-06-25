package techzo.com.example.cambiazo.exchanges.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllBenefitsByMembershipIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllBenefitsQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetBenefitByIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.BenefitCommandService;
import techzo.com.example.cambiazo.exchanges.domain.services.BenefitQueryService;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.BenefitResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateBenefitResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.BenefitResourceFromEntityAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.CreateBenefitCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Controller class for managing Benefit operations through REST endpoints.
 *
 * @author CambiaZo - TechZo
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/benefits")
@Tag(name="Benefits", description="Benefits Management Endpoints")
public class BenefitController {

    private final BenefitCommandService benefitCommandService;
    private final BenefitQueryService benefitQueryService;

    /**
     * Constructor for BenefitController.
     *
     * @param benefitCommandService Service handling commands related to Benefits.
     * @param benefitQueryService   Service handling queries related to Benefits.
     */
    public BenefitController(BenefitCommandService benefitCommandService, BenefitQueryService benefitQueryService){
        this.benefitCommandService = benefitCommandService;
        this.benefitQueryService = benefitQueryService;
    }

    /**
     * Endpoint for creating a new Benefit.
     *
     * @param resource The resource containing data to create the Benefit.
     * @return ResponseEntity containing the created BenefitResource.
     */
    @Operation(summary="Create a new Benefit", description="Create a new Benefit with the input data.")
    @PostMapping
    public ResponseEntity<BenefitResource> createBenefit(@RequestBody CreateBenefitResource resource){
        try {
            var createBenefitCommand = CreateBenefitCommandFromResourceAssembler.toCommandFromResource(resource);
            var benefit = benefitCommandService.handle(createBenefitCommand);
            var benefitResource = BenefitResourceFromEntityAssembler.toResourceFromEntity(benefit.get());
            return ResponseEntity.status(CREATED).body(benefitResource);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for retrieving all Benefits.
     *
     * @return ResponseEntity containing a list of BenefitResources.
     */
    @Operation(summary="Get all Benefits", description="Get all Benefits.")
    @GetMapping
    public ResponseEntity<List<BenefitResource>> getAllBenefits(){
        try {
            var getAllBenefitsQuery = new GetAllBenefitsQuery();
            var benefits = benefitQueryService.handle(getAllBenefitsQuery);
            var benefitResources = benefits.stream()
                    .map(BenefitResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(benefitResources);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for retrieving a Benefit by its ID.
     *
     * @param id The ID of the Benefit to retrieve.
     * @return ResponseEntity containing the retrieved BenefitResource.
     */
    @Operation(summary="Get a Benefit by ID", description="Get a Benefit with the input id.")
    @GetMapping("/{id}")
    public ResponseEntity<BenefitResource> getBenefit(@PathVariable Long id){
        try {
            var getBenefitByIdQuery = new GetBenefitByIdQuery(id);
            var benefit = benefitQueryService.handle(getBenefitByIdQuery);
            var benefitResource = BenefitResourceFromEntityAssembler.toResourceFromEntity(benefit.get());
            return ResponseEntity.ok(benefitResource);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for retrieving all Benefits by Membership ID.
     *
     * @param id The ID of the Membership to retrieve Benefits for.
     * @return ResponseEntity containing a list of BenefitResources.
     */
    @Operation(summary="Get all Benefits by Membership", description="Get all Benefits by Membership.")
    @GetMapping("/membership/{id}")
    public ResponseEntity<List<BenefitResource>> getBenefitsByMembership(@PathVariable Long id){
        try {
            var getBenefitsByMembershipQuery = new GetAllBenefitsByMembershipIdQuery(id);
            var benefits = benefitQueryService.handle(getBenefitsByMembershipQuery);
            var benefitResources = benefits.stream()
                    .map(BenefitResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(benefitResources);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for deleting a Benefit by its ID.
     *
     * @param id The ID of the Benefit to delete.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @Operation(summary="Delete a Benefit", description="Delete a Benefit with the input id.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBenefit(@PathVariable Long id){
        try {
            benefitCommandService.handleDeleteBenefit(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
