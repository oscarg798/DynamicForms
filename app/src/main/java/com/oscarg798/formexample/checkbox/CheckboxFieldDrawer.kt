package com.oscarg798.formexample.checkbox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.oscarg798.formexample.fielddefinition.Field
import com.oscarg798.formexample.fielddrawer.FieldDrawer

class CheckboxFieldDrawer : FieldDrawer<CheckboxFieldType, Map<String, Boolean>> {

    override val supportedType: CheckboxFieldType
        get() = CheckboxFieldType

    @Composable
    override fun Draw(
        field: Field<CheckboxFieldType, Map<String, Boolean>>,
        value: Field.FieldValue<Map<String, Boolean>>,
        onValueChange: (Field.FieldValue<Map<String, Boolean>>) -> Unit
    ) {
        CheckboxFieldUI(field, value) {
            onValueChange(it)
        }
    }
}

@Composable
private fun CheckboxFieldUI(
    field: Field<CheckboxFieldType, Map<String, Boolean>>,
    value: Field.FieldValue<Map<String, Boolean>>, onValueChange: (Field.FieldValue<Map<String, Boolean>>) -> Unit
) {
    Text(text = field.name.capitalize(Locale.current))
    Column() {
        (field as CheckboxField).options.map { option ->
            Row(Modifier.fillMaxWidth()) {
                Checkbox(
                    checked = value.value[option.id] ?: false,
                    onCheckedChange = {
                        val currentValue = value.value.toMutableMap()
                        currentValue[option.id] = it
                        onValueChange(Field.FieldValue(field.id, currentValue))
                    }
                )

                Text(option.label)
            }
        }
    }
}