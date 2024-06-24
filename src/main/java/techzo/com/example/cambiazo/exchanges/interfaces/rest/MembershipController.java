package techzo.com.example.cambiazo.exchanges.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Membership;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllMembershipsQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetMembershipByIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.MembershipCommandService;
import techzo.com.example.cambiazo.exchanges.domain.services.MembershipQueryService;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateMembershipResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.MembershipResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.CreateMembershipCommandFromResourceAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.MembershipResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Controller class for managing Membership operations through REST endpoints.
 *
 * @author CambiaZo - TechZo
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/membership")
@Tag(name="Memberships", description="Memberships Management Endpoints")
public class MembershipController {

    private final MembershipCommandService membershipCommandService;
    private final MembershipQueryService membershipQueryService;

    /**
     * Constructor for MembershipController.
     *
     * @param membershipCommandService Service handling commands related to Membership.
     * @param membershipQueryService Service handling queries related to Membership.
     */
    public MembershipController(MembershipCommandService membershipCommandService, MembershipQueryService membershipQueryService){
        this.membershipCommandService = membershipCommandService;
        this.membershipQueryService = membershipQueryService;
    }

    /**
     * Endpoint for creating a new Membership.
     *
     * @param resource The resource containing data to create the Membership.
     * @return ResponseEntity containing the created MembershipResource.
     */
    @Operation(summary="Create a new Membership", description="Create a new Membership with the input data.")
    @PostMapping
    public ResponseEntity<MembershipResource> createPlan(@RequestBody CreateMembershipResource resource){
        Optional<Membership> membership = membershipCommandService.handle(CreateMembershipCommandFromResourceAssembler.toCommandFromResource(resource));
        return membership.map(source -> new ResponseEntity<>(MembershipResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint for retrieving all Memberships.
     *
     * @return ResponseEntity containing a list of MembershipResources.
     */
    @GetMapping
    public ResponseEntity<List<MembershipResource>> getAllMemberships(){
        var getAllMembershipsQuery = new GetAllMembershipsQuery();
        var membership = membershipQueryService.handle(getAllMembershipsQuery);
        var membershipResource = membership.stream().map(MembershipResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(membershipResource);
    }

    /**
     * Endpoint for retrieving a Membership by its ID.
     *
     * @param id The ID of the Membership to retrieve.
     * @return ResponseEntity containing the retrieved MembershipResource.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MembershipResource> getMembershipById(@PathVariable Long id){
        Optional<Membership> membership = membershipQueryService.handle(new GetMembershipByIdQuery(id));
        return membership.map(source -> new ResponseEntity<>(MembershipResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint for deleting a Membership by its ID.
     *
     * @param id The ID of the Membership to delete.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMembership(@PathVariable Long id){
        try {
            membershipCommandService.handleDeleteMembership(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
