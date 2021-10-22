package com.oscarg798.formexample.switch

import androidx.compose.runtime.Stable
import com.oscarg798.formexample.fielddefinition.Field

@Stable
data class SwitchField(
    override val id: String,
    override val name: String,
    override val isRequired: Boolean,
    override val value: FieldValue<Boolean>
) : Field<Switch, Boolean>(
    id = id,
    name = name,
    isRequired = isRequired,
    value = value,
    type = Switch
) {

    override fun mutate(value: FieldValue<Boolean>): Field<Switch, Boolean> {
        return copy(value = value)
    }
}