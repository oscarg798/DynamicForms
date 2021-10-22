package com.oscarg798.formexample.fielddrawer

import androidx.compose.runtime.Composable
import com.oscarg798.formexample.fielddefinition.Field
import com.oscarg798.formexample.fielddefinition.FieldType

class FormDrawer(private val childDrawers: List<FieldDrawer<FieldType, Any>>) {

    @Composable
    fun Draw(
        field: Field<FieldType, Any>,
        onValueChange: (Field.FieldValue<Any>) -> Unit
    ) {
        childDrawers.first {
            it.supportedType == field.type
        }.Draw(
            field = field,
            value = field.value,
            onValueChange = onValueChange
        )
    }
}