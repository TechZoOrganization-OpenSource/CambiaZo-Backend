package techzo.com.example.cambiazo.donations.interfaces.rest.transform;

import techzo.com.example.cambiazo.donations.domain.model.aggregates.AccountNumber;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.AccountNumberResource;

public class AccountNumberResourceFromEntityAssembler {

    public static AccountNumberResource toResourceFromEntity(AccountNumber entity) {
        return new AccountNumberResource(entity.getId(), entity.getName(), entity.getCci(), entity.getAccount(), entity.getOngId());
    }
}
