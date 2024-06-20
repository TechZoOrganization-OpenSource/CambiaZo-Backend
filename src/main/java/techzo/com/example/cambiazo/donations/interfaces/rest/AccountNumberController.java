package techzo.com.example.cambiazo.donations.interfaces.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import techzo.com.example.cambiazo.donations.domain.services.AccountNumberCommandService;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.AccountNumberResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CreateAccountNumberResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.AccountNumberResourceFromEntityAssembler;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.CreateAccountNumberCommandFromResourceAssembler;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/account-number")
@Tag(name="Account Number", description="Account Number Management Endpoints")
public class AccountNumberController {

    private final AccountNumberCommandService accountNumberCommandService;

    public AccountNumberController(AccountNumberCommandService accountNumberCommandService){
        this.accountNumberCommandService=accountNumberCommandService;
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
}
