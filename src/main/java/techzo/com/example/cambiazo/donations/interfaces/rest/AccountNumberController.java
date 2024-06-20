package techzo.com.example.cambiazo.donations.interfaces.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAccountNumberByIdQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllAccountNumberByOngIdQuery;
import techzo.com.example.cambiazo.donations.domain.services.AccountNumberCommandService;
import techzo.com.example.cambiazo.donations.domain.services.AccountNumberQueryService;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.AccountNumberResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CreateAccountNumberResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.AccountNumberResourceFromEntityAssembler;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.CreateAccountNumberCommandFromResourceAssembler;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.SocialNetworkResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/account-number")
@Tag(name="Account Number", description="Account Number Management Endpoints")
public class AccountNumberController {

    private final AccountNumberCommandService accountNumberCommandService;

    private final AccountNumberQueryService accountNumberQueryService;

    public AccountNumberController(AccountNumberCommandService accountNumberCommandService, AccountNumberQueryService accountNumberQueryService){
        this.accountNumberCommandService=accountNumberCommandService;
        this.accountNumberQueryService=accountNumberQueryService;
    }

    @Operation(summary="Create a new Account Number", description="Create a new Account Number with the input data.")
    @PostMapping
    public ResponseEntity<AccountNumberResource>createAccountNumber(@RequestBody CreateAccountNumberResource resource){
        try {
            var createAccountNumberCommand= CreateAccountNumberCommandFromResourceAssembler.toCommandFromResource(resource);
            var accountNumber=accountNumberCommandService.handle(createAccountNumberCommand);
            var accountNumberResource= AccountNumberResourceFromEntityAssembler.toResourceFromEntity(accountNumber.get());
            return ResponseEntity.status(CREATED).body(accountNumberResource);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }


    @Operation(summary="Get Account Number by Id", description="Get Account Number by Id.")
    @GetMapping("/{id}")
    public ResponseEntity<AccountNumberResource> getAccountNumberById(@PathVariable Long id){
        try {
            var getAccountNumberByIdQuery=new GetAccountNumberByIdQuery(id);
            var accountNumber=accountNumberQueryService.handle(getAccountNumberByIdQuery);
            var accountNumberResource= AccountNumberResourceFromEntityAssembler.toResourceFromEntity(accountNumber.get());
            return ResponseEntity.ok(accountNumberResource);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }


    @Operation(summary="Get All Account Number by Ong Id", description="Get All Account Number by Ong Id.")
    @GetMapping("/ongs/{ongId}")
    public ResponseEntity<List<AccountNumberResource>> getAllAccountNumberByOngId(@PathVariable Long ongId){
        try {
            var getAllAccountNumberByOngIdQuery=new GetAllAccountNumberByOngIdQuery(ongId);
            var accountNumber=accountNumberQueryService.handle(getAllAccountNumberByOngIdQuery);
            var accountNumberResource= accountNumber.stream().map(AccountNumberResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(accountNumberResource);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary="Delete Account Number", description="Delete Account Number by Id.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccountNumber(@PathVariable Long id){
        try {
            accountNumberCommandService.handleDeleteAccountNumber(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

}
