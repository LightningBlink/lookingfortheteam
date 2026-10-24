package com.den.constraint;
import com.den.constraint.validatedBy.CheckLengthWithEmpty;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validate that the string is between min and max included.
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 */
@Documented
@Constraint(validatedBy = {CheckLengthWithEmpty.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface LengthWithEmpty {

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    String message() default "{org.hibernate.validator.constraints.LengthWithEmpty.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * Defines several {@code @Length} annotations on the same element.
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        LengthWithEmpty[] value();
    }
}
