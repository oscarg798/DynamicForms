package com.oscarg798.formexample.writer

import com.oscarg798.formexample.fielddefinition.Field
import com.oscarg798.formexample.fielddefinition.FieldType

class FormWriter(
    private val childWriters: List<FieldValueWriter<Any>>,
    private  val fieldTypes: Map<String, FieldType>
) {

    fun write(values: List<Field.FieldValue<Any>>): String {
        return values.joinToString(",") { fieldValue ->
            childWriters.first {
                it.supportedType == fieldTypes[fieldValue.id]
            }.write(fieldValue)
        }
    }
}