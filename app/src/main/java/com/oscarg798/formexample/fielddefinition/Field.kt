package com.oscarg798.formexample.fielddefinition

import androidx.compose.runtime.Composable

abstract class Field<Type: FieldType, ValueType>(
    open val id: String,
    open val name: String,
    open val isRequired: Boolean,
    open val value: FieldValue<ValueType>,
    val type: Type,
) {

    abstract fun mutate(value: FieldValue<ValueType>): Field<Type, ValueType>

    data class FieldValue<ValueType>(
        val id: String,
        val value: ValueType
    )
}









