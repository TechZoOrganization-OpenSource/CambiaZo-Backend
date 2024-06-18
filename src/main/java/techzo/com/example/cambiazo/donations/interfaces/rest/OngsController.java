package techzo.com.example.cambiazo.donations.interfaces.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import techzo.com.example.cambiazo.donations.domain.services.OngCommandService;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CreateOngResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.OngResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.CreateOngCommandFromResourceAssembler;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.OngResourceFromEntityAssembler;

@RestController
@RequestMapping(value = "/api/v1/ongs", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Ongs", description = "Ongs Management Endpoints")
public class OngsController {

    private final OngCommandService ongCommandService;

    public OngsController(OngCommandService ongCommandService) {
        this.ongCommandService = ongCommandService;
    }

    @Operation(summary = "Create a new Ong", description = "Create a new Ong with the input data.")
    @PostMapping
    public ResponseEntity<OngResource>createOng(@RequestBody CreateOngResource resource){
        try{
            var createOngCommand = CreateOngCommandFromResourceAssembler.toCommandFromResource(resource);
            var ong = ongCommandService.handle(createOngCommand);
            var ongResource = OngResourceFromEntityAssembler.toResourceFromEntity(ong.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(ongResource); //201
        }catch (IllegalArgumentException e){ //404
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

}
