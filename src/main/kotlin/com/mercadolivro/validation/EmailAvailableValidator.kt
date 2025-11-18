package com.mercadolivro.validation

import com.mercadolivro.service.CustomerServiceImpl
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EmailAvailableValidator(
    val customerServiceImpl: CustomerServiceImpl
): ConstraintValidator<EmailAvailable, String> {

    override fun isValid(value: String?, constraintValidatorContext: ConstraintValidatorContext?): Boolean {

        if(value.isNullOrEmpty()){
            return false
        }
        return customerServiceImpl.emailAvailable(value)
    }
}
