package com.oscarg798.formexample.switch

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oscarg798.formexample.fielddefinition.Field
import com.oscarg798.formexample.fielddrawer.FieldDrawer

class SwitchDrawer : FieldDrawer<Switch, Boolean> {

    override val supportedType: Switch
        get() = Switch

    @Composable
    override fun Draw(
        field: Field<Switch, Boolean>,
        value: Field.FieldValue<Boolean>,
        onValueChange: (Field.FieldValue<Boolean>) -> Unit
    ) {
        Row(
            Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(text = field.name)
            Switch(
                checked = value.value, onCheckedChange = { checked ->
                    onValueChange(Field.FieldValue(value.id, checked))
                }, modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(.1f)
            )
        }
    }


}