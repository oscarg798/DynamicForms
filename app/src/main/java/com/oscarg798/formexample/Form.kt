package com.oscarg798.formexample

import com.oscarg798.formexample.fielddefinition.Field
import com.oscarg798.formexample.fielddefinition.FieldType

data class Form(val fields: List<Field<FieldType, Any>>)
