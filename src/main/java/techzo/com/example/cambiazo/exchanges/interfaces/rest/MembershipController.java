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

@RestController
@RequestMapping("/api/v1/membership")
@Tag(name="Memberships", description="Memberships Management Endpoints")
public class MembershipController {

    private final MembershipCommandService membershipCommandService;

    private final MembershipQueryService membershipQueryService;

    public MembershipController(MembershipCommandService membershipCommandService, MembershipQueryService membershipQueryService){
        this.membershipCommandService = membershipCommandService;
        this.membershipQueryService = membershipQueryService;
    }

    @Operation(summary="Create a new Membership", description="Create a new Membership with the input data.")
    @PostMapping
    public ResponseEntity<MembershipResource>createPlan(@RequestBody CreateMembershipResource resource){
        Optional<Membership> membership= membershipCommandService.handle(CreateMembershipCommandFromResourceAssembler.toCommandFromResource(resource));
        return membership.map(source->new ResponseEntity<>(MembershipResourceFromEntityAssembler.toResourceFromEntity(source),CREATED)).orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MembershipResource>>getAllMemberships(){
        var getAllMembershipsQuery=new GetAllMembershipsQuery();
        var membership = membershipQueryService.handle(getAllMembershipsQuery);
        var membershipResource=membership.stream().map(MembershipResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(membershipResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipResource>getMembershipById(@PathVariable Long id){
        Optional<Membership> membership = membershipQueryService.handle(new GetMembershipByIdQuery(id));
        return membership.map(source->new ResponseEntity<>(MembershipResourceFromEntityAssembler.toResourceFromEntity(source),CREATED)).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>deleteMembership(@PathVariable Long id){
        try {
            membershipCommandService.handleDeleteMembership(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
