package com.oscarg798.formexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.oscarg798.formexample.checkbox.CheckboxField
import com.oscarg798.formexample.checkbox.CheckboxFieldDrawer
import com.oscarg798.formexample.checkbox.CheckboxFieldType
import com.oscarg798.formexample.checkbox.CheckboxWriter
import com.oscarg798.formexample.fielddefinition.Field
import com.oscarg798.formexample.fielddefinition.FieldType
import com.oscarg798.formexample.fielddrawer.FieldDrawer
import com.oscarg798.formexample.fielddrawer.FormDrawer
import com.oscarg798.formexample.switch.Switch
import com.oscarg798.formexample.switch.SwitchDrawer
import com.oscarg798.formexample.switch.SwitchField
import com.oscarg798.formexample.switch.SwitchWriter
import com.oscarg798.formexample.text.Text
import com.oscarg798.formexample.text.TextDrawer
import com.oscarg798.formexample.text.TextField
import com.oscarg798.formexample.text.TextWriter
import com.oscarg798.formexample.writer.FieldValueWriter
import com.oscarg798.formexample.writer.FormWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val formFlow = MutableStateFlow(
        Form(
            listOf(
                TextField("1", "Field 1", true, Field.FieldValue("1", "")) as Field<FieldType, Any>,
                SwitchField(
                    "2",
                    "My switch",
                    false,
                    value = Field.FieldValue("2", false)
                ) as Field<FieldType, Any>,
                CheckboxField(
                    "44",
                    "checkbox",
                    false,
                    value = Field.FieldValue("44", mapOf()),
                    listOf(
                        CheckboxField.Option("1", "amigos"),
                        CheckboxField.Option("2", "enemigos"),
                        CheckboxField.Option("3", "rompe corazones")
                    )
                ) as Field<FieldType, Any>,
                TextField("5", "Field 2", true, Field.FieldValue("5", "amigos")) as Field<FieldType, Any>
            )
        )
    )

    private val formDrawer = FormDrawer(
        listOf(
            TextDrawer() as FieldDrawer<FieldType, Any>,
            SwitchDrawer() as FieldDrawer<FieldType, Any>,
            CheckboxFieldDrawer() as FieldDrawer<FieldType, Any>
        )
    )

    private val formWriter = FormWriter(
        listOf(
            TextWriter() as FieldValueWriter<Any>,
            SwitchWriter() as FieldValueWriter<Any>,
            CheckboxWriter() as FieldValueWriter<Any>
        ),
        getTypes(formFlow.value)
    )
    private val output = MutableStateFlow("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenResumed {
            output.value = getValuesAsString()
        }

        findViewById<ComposeView>(R.id.main).setContent {
            val text by output.collectAsState()
            val form by formFlow.collectAsState()

            val fields = form.fields

            Surface {
                Column(Modifier.padding(16.dp)) {
                    Text(text = text)

                    LazyColumn {
                        items(fields, key = { it.id }) { field ->
                            formDrawer.Draw(field = field, onValueChange = {
                                onValueChange(it)
                            })
                        }
                    }
                }
            }
        }
    }

    private fun getTypes(form: Form): Map<String, FieldType> {
        return form.fields.associate {
            Pair(it.id, it.type as FieldType)
        }
    }

    private fun getValuesAsString(): String {
        return """
            [
            ${formWriter.write(formFlow.value.fields.map { it.value })}
            ]
        """.trimIndent()
    }

    private fun onValueChange(fieldValue: Field.FieldValue<Any>) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val fields = formFlow.value.fields.toMutableList()
                val fieldIndex = fields.indexOfFirst { field -> fieldValue.id == field.id }
                val fieldToUpdate = fields[fieldIndex]
                fields.removeAt(fieldIndex)
                fields.add(fieldIndex, fieldToUpdate.mutate(fieldValue))
                formFlow.value = formFlow.value.copy(fields = fields)
                output.value = getValuesAsString()
            }
        }
    }
}