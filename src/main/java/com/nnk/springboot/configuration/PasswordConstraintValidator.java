package com.nnk.springboot.configuration;

import com.google.common.base.Joiner;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.passay.*;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(final ValidPassword arg0) {

    }

    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
        // @formatter:off
        final PasswordValidator validator = new PasswordValidator(Arrays.asList(
                //At least eight letters
                new LengthRule(8, 100),
                //At least one upper case letter
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                //At least one lower case letter
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                //At least one number
                new CharacterRule(EnglishCharacterData.Digit, 1),
                //At least one special characters
                new CharacterRule(EnglishCharacterData.Special, 1),

                new WhitespaceRule(MatchBehavior.Contains)
        )
        );

        final RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(Joiner.on(",").join(validator.getMessages(result))).addConstraintViolation();
        return false;
    }

}