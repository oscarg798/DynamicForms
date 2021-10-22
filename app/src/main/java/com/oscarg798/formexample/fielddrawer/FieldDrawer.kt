package com.oscarg798.formexample.fielddrawer

import androidx.compose.runtime.Composable
import com.oscarg798.formexample.fielddefinition.Field
import com.oscarg798.formexample.fielddefinition.FieldType

interface FieldDrawer<Type: FieldType, Valuetype> {

    val supportedType: Type

    @Composable
    fun Draw(
        field: Field<Type, Valuetype>,
        value: Field.FieldValue<Valuetype>,
        onValueChange: (Field.FieldValue<Valuetype>) -> Unit
    )
}