package com.oscarg798.formexample.switch

import com.oscarg798.formexample.fielddefinition.Field
import com.oscarg798.formexample.fielddefinition.FieldType
import com.oscarg798.formexample.writer.FieldValueWriter

class SwitchWriter : FieldValueWriter<Boolean> {

    override val supportedType: FieldType
        get() = Switch

    override fun write(value: Field.FieldValue<Boolean>): String {
        return """{
            "id": "${value.id}",
            "booleanValue" : ${value.value}
            }
        """.trimIndent()
    }
}
