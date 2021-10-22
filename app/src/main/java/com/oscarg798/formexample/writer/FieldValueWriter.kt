package com.oscarg798.formexample.writer

import com.oscarg798.formexample.fielddefinition.Field
import com.oscarg798.formexample.fielddefinition.FieldType

interface FieldValueWriter<ValueType> {

    val supportedType: FieldType

    fun write(value: Field.FieldValue<ValueType>): String
}