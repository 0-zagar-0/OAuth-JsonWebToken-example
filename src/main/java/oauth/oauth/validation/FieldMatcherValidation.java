package oauth.oauth.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatcherValidation implements ConstraintValidator<FieldMatcher, Object> {
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldMatcher constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMacthValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

        if (fieldValue != null) {
            return fieldValue.equals(fieldMacthValue);
        } else {
            return fieldMacthValue == null;
        }
    }
}
