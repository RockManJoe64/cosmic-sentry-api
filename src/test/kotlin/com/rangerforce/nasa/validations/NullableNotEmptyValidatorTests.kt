package com.rangerforce.nasa.validations

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import jakarta.validation.ConstraintValidatorContext

class NullableNotEmptyValidatorTests : StringSpec({

    val validator = NullableNotEmptyValidator()
    val constraintValidatorContext = mockk<ConstraintValidatorContext>()

    "should validate various inputs correctly" {
        forAll(
            row(null, true),
            row("not empty", true),
            row("", false)
        ) { value, expected ->
            validator.isValid(value, constraintValidatorContext) shouldBe expected
        }
    }
})
