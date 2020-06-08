package pl.clinic.util.validator;

import org.springframework.stereotype.Component;
import pl.clinic.account.model.AccountRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements
        ConstraintValidator<UniqueUsername, String> {

    private AccountRepository accountRepository;

    public UniqueUsernameValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return accountRepository.findByUsername(value) == null;
    }
}
