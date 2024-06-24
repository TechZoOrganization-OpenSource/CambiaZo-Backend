package techzo.com.example.cambiazo.exchanges.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllCountriesQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetCountryByIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.CountryCommandService;
import techzo.com.example.cambiazo.exchanges.domain.services.CountryQueryService;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CountryResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateCountryResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.CountryResourceFromEntityAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.CreateCountryCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Controller class for managing Country operations through REST endpoints.
 *
 * @author CambiaZo - TechZo
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/api/v1/countries")
@Tag(name="Countries", description="Countries Management Endpoints")
public class CountryController {

    private final CountryCommandService countryCommandService;
    private final CountryQueryService countryQueryService;

    /**
     * Constructor for CountryController.
     *
     * @param countryCommandService Service handling commands related to Countries.
     * @param countryQueryService   Service handling queries related to Countries.
     */
    public CountryController(CountryCommandService countryCommandService, CountryQueryService countryQueryService){
        this.countryCommandService = countryCommandService;
        this.countryQueryService = countryQueryService;
    }

    /**
     * Endpoint for creating a new Country.
     *
     * @param resource The resource containing data to create the Country.
     * @return ResponseEntity containing the created CountryResource.
     */
    @Operation(summary="Create a new Country", description="Create a new Country with the input data.")
    @PostMapping
    public ResponseEntity<CountryResource> createCountry(@RequestBody CreateCountryResource resource){
        try {
            var createCountryCommand = CreateCountryCommandFromResourceAssembler.toCommandFromResource(resource);
            var country = countryCommandService.handle(createCountryCommand);
            var countryResource = CountryResourceFromEntityAssembler.toResourceFromEntity(country.get());
            return ResponseEntity.status(CREATED).body(countryResource);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for retrieving a Country by its ID.
     *
     * @param id The ID of the Country to retrieve.
     * @return ResponseEntity containing the retrieved CountryResource.
     */
    @Operation(summary="Get Country by ID", description="Get Country by ID.")
    @GetMapping("/{id}")
    public ResponseEntity<CountryResource> getCountryById(@PathVariable Long id){
        try {
            var getCountryById = new GetCountryByIdQuery(id);
            var country = countryQueryService.handle(getCountryById);
            var countryResource = CountryResourceFromEntityAssembler.toResourceFromEntity(country.get());
            return ResponseEntity.ok(countryResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for retrieving all Countries.
     *
     * @return ResponseEntity containing a list of CountryResources.
     */
    @Operation(summary="Get all Countries", description="Get all Countries.")
    @GetMapping
    public ResponseEntity<List<CountryResource>> getAllCountries(){
        try {
            var getAllCountries = new GetAllCountriesQuery();
            var countries = countryQueryService.handle(getAllCountries);
            var countryResources = countries.stream()
                    .map(CountryResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(countryResources);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for deleting a Country by its ID.
     *
     * @param id The ID of the Country to delete.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @Operation(summary="Delete a Country", description="Delete a Country with the input id.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id){
        try {
            countryCommandService.handleDeleteCountry(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
