package techzo.com.example.cambiazo.exchanges.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.exchanges.domain.services.BenefitCommandService;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.BenefitResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateBenefitResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.BenefitResourceFromEntityAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.CreateBenefitCommandFromResourceAssembler;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/benefits")
@Tag(name="Benefits", description="Benefits Management Endpoints")
public class BenefitController {

    private final BenefitCommandService benefitCommandService;

    public BenefitController(BenefitCommandService benefitCommandService){
        this.benefitCommandService=benefitCommandService;
    }

    @Operation(summary="Create a new Benefit", description="Create a new Benefit with the input data.")
    @PostMapping
    public ResponseEntity<BenefitResource>createBenefit(@RequestBody CreateBenefitResource resource){
        try {
            var createBenefitCommand= CreateBenefitCommandFromResourceAssembler.toCommandFromResource(resource);
            var benefit=benefitCommandService.handle(createBenefitCommand);
            var benefitResource= BenefitResourceFromEntityAssembler.toResourceFromEntity(benefit.get());
            return ResponseEntity.status(CREATED).body(benefitResource);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary="Delete a Benefit", description="Delete a Benefit with the input id.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBenefit(@PathVariable Long id){
        try {
            benefitCommandService.handleDeleteBenefit(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
