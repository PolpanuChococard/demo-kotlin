package com.example.demo_kotlin.helper

import io.konform.validation.Validation
import io.konform.validation.ValidationResult
import io.konform.validation.jsonschema.pattern
import io.konform.validation.string.notBlank

class ValidatorUtil {
    fun validateEmail(value:String) : ValidationResult<ValidatorData<String>> {
        val validateEmail = Validation<ValidatorData<String>> {
            ValidatorData<String>::data {
                notBlank()
                pattern(".+@.+.+")
            }
        }

        val data = ValidatorData<kotlin.String>(data = value);
        return validateEmail(data);
    }

}

data class ValidatorData<T>(
    val data: T
);