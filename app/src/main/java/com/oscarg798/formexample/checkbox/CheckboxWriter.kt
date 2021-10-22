package com.oscarg798.formexample.checkbox

import com.oscarg798.formexample.fielddefinition.Field
import com.oscarg798.formexample.fielddefinition.FieldType
import com.oscarg798.formexample.writer.FieldValueWriter


class CheckboxWriter: FieldValueWriter<Map<String, Boolean>> {
    override val supportedType: FieldType
        get() = CheckboxFieldType

    override fun write(value: Field.FieldValue<Map<String, Boolean>>): String {
        return """{
             "id": "${value.id}",
            "checkboxValue" : ${value.value}
            }
        """.trimIndent()
    }



}