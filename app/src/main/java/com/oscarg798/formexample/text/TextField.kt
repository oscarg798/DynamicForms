package com.oscarg798.formexample.text

import com.oscarg798.formexample.fielddefinition.Field

data class TextField(
    override val id: String,
    override val name: String,
    override val isRequired: Boolean,
    override var value: Field.FieldValue<String>
) : Field<Text, String>(
    id = id,
    name = name,
    isRequired = isRequired,
    value = value,
    type = Text
) {

    override fun mutate(value: FieldValue<String>): Field<Text, String> {
        return copy(value = value)
    }
}