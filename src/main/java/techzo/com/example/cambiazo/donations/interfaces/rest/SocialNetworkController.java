package techzo.com.example.cambiazo.donations.interfaces.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.donations.domain.services.SocialNetworkCommandService;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CreateSocialNetworkResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.SocialNetworkResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.CreateSocialNetworkCommandFromResourceAssembler;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.SocialNetworkResourceFromEntityAssembler;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/social-networks")
@Tag(name="Social Networks", description="Social Networks Management Endpoints")
public class SocialNetworkController {

    private final SocialNetworkCommandService socialNetworkCommandService;

    public SocialNetworkController(SocialNetworkCommandService socialNetworkCommandService){
        this.socialNetworkCommandService=socialNetworkCommandService;
    }

    @Operation(summary="Create a new Social Network", description="Create a new Social Network with the input data.")
    @PostMapping
    public ResponseEntity<SocialNetworkResource>createSocialNetwork(@RequestBody CreateSocialNetworkResource resource){
        try {
            var createSocialNetworkCommand= CreateSocialNetworkCommandFromResourceAssembler.toCommandFromResource(resource);
            var socialNetwork=socialNetworkCommandService.handle(createSocialNetworkCommand);
            var socialNetworkResource= SocialNetworkResourceFromEntityAssembler.toResourceFromEntity(socialNetwork.get());
            return ResponseEntity.status(CREATED).body(socialNetworkResource);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
