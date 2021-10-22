package com.oscarg798.formexample.text

import com.oscarg798.formexample.fielddefinition.Field
import com.oscarg798.formexample.fielddefinition.FieldType
import com.oscarg798.formexample.writer.FieldValueWriter

class TextWriter : FieldValueWriter<String> {

    override val supportedType: FieldType
        get() = Text

    override fun write(value: Field.FieldValue<String>): String {
        return """{
             "id": "${value.id}",
            "textValue" : ${value.value}
            }
        """.trimIndent()
    }
}