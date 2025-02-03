package com.rangerforce.nasa.validations

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class NullableNotEmptyValidator : ConstraintValidator<NullableNotEmpty, String?> {
    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        return value == null || value.isNotEmpty()
    }
}
