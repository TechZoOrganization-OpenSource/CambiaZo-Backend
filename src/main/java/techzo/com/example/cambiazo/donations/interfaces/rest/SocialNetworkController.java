package techzo.com.example.cambiazo.donations.interfaces.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllSocialNetworkByOngIdQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllSocialNetworksQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetSocialNetworkByIdQuery;
import techzo.com.example.cambiazo.donations.domain.services.SocialNetworkCommandService;
import techzo.com.example.cambiazo.donations.domain.services.SocialNetworkQueryService;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CreateSocialNetworkResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.SocialNetworkResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.CreateSocialNetworkCommandFromResourceAssembler;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.SocialNetworkResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/social-networks")
@Tag(name="Social Networks", description="Social Networks Management Endpoints")
public class SocialNetworkController {

    private final SocialNetworkCommandService socialNetworkCommandService;

    private final SocialNetworkQueryService socialNetworkQueryService;

    public SocialNetworkController(SocialNetworkCommandService socialNetworkCommandService, SocialNetworkQueryService socialNetworkQueryService){
        this.socialNetworkCommandService=socialNetworkCommandService;
        this.socialNetworkQueryService=socialNetworkQueryService;
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

    @Operation(summary="Get all Social Networks", description="Get all Social Networks.")
    @GetMapping
    public ResponseEntity<List<SocialNetworkResource>> getAllSocialNetworks(){
        try {
            var getAllSocialNetworksQuery=new GetAllSocialNetworksQuery();
            var socialNetworks=socialNetworkQueryService.handle(getAllSocialNetworksQuery);
            var socialNetworkResources=socialNetworks.stream().map(SocialNetworkResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(socialNetworkResources);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary="Get a Social Network by Id", description="Get a Social Network by Id.")
    @GetMapping("/{id}")
    public ResponseEntity<SocialNetworkResource> getSocialNetworkById(@PathVariable Long id){
        try {
            var getSocialNetworkByIdQuery=new GetSocialNetworkByIdQuery(id);
            var socialNetwork=socialNetworkQueryService.handle(getSocialNetworkByIdQuery);
            var socialNetworkResource=SocialNetworkResourceFromEntityAssembler.toResourceFromEntity(socialNetwork.get());
            return ResponseEntity.ok(socialNetworkResource);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary="Get Social Networks by Ong Id", description="Get Social Networks by Ong Id.")
    @GetMapping("/ongs/{id}")
    public ResponseEntity<List<SocialNetworkResource>> getSocialNetworksByOngId(@PathVariable Long id){
        try {
            var getSocialNetworksByOngIdQuery=new GetAllSocialNetworkByOngIdQuery(id);
            var socialNetworks=socialNetworkQueryService.handle(getSocialNetworksByOngIdQuery);
            var socialNetworkResources=socialNetworks.stream().map(SocialNetworkResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(socialNetworkResources);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSocialNetwork(@PathVariable Long id){
        try {
            socialNetworkCommandService.handleDeleteSocialNetwork(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

}
