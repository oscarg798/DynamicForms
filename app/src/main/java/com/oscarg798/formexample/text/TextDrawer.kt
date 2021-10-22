package com.oscarg798.formexample.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oscarg798.formexample.fielddefinition.Field
import com.oscarg798.formexample.fielddrawer.FieldDrawer

class TextDrawer : FieldDrawer<Text, String> {

    override val supportedType: Text
        get() = Text

    @Composable
    override fun Draw(
        field: Field<Text, String>,
        value: Field.FieldValue<String>,
        onValueChange: (Field.FieldValue<String>) -> Unit
    ) {

        TextField(value = value.value,
            label = {
                Text(text = field.name)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onValueChange = {
                onValueChange(Field.FieldValue(value.id, it))
            })
    }


}