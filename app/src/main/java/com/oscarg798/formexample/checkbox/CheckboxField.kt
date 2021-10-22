package com.oscarg798.formexample.checkbox

import com.oscarg798.formexample.fielddefinition.Field

data class CheckboxField(
    override val id: String,
    override val name: String,
    override val isRequired: Boolean,
    override var value: FieldValue<Map<String, Boolean>>,
    val options: List<Option>
) : Field<CheckboxFieldType, Map<String, Boolean>>(
    id = id,
    name = name,
    isRequired = isRequired,
    value = value,
    type = CheckboxFieldType
) {


    override fun mutate(value: FieldValue<Map<String, Boolean>>): Field<CheckboxFieldType, Map<String, Boolean>> {
        return copy(value = value)
    }

    data class Option(val id: String, val label: String)
}