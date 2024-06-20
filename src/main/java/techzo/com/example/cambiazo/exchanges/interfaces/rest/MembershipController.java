package techzo.com.example.cambiazo.exchanges.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Membership;
import techzo.com.example.cambiazo.exchanges.domain.services.MembershipCommandService;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateMembershipResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.MembershipResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.CreateMembershipCommandFromResourceAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.MembershipResourceFromEntityAssembler;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/membership")
@Tag(name="Memberships", description="Memberships Management Endpoints")
public class MembershipController {

    private final MembershipCommandService membershipCommandService;

    public MembershipController(MembershipCommandService membershipCommandService){
        this.membershipCommandService = membershipCommandService;
    }

    @Operation(summary="Create a new Membership", description="Create a new Membership with the input data.")
    @PostMapping
    public ResponseEntity<MembershipResource>createPlan(@RequestBody CreateMembershipResource resource){
        Optional<Membership> membership= membershipCommandService.handle(CreateMembershipCommandFromResourceAssembler.toCommandFromResource(resource));
        return membership.map(source->new ResponseEntity<>(MembershipResourceFromEntityAssembler.toResourceFromEntity(source),CREATED)).orElseGet(()->ResponseEntity.notFound().build());
    }
}
