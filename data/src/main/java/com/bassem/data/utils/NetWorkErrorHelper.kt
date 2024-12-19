package com.bassem.data.utils

import com.bassem.core.entity.ErrorTypes
import com.google.gson.JsonParseException
import java.io.IOException
import java.sql.SQLException

fun Exception.getExceptionMessage() = when (this) {
    is IOException -> ErrorTypes.IoException
    is SQLException -> ErrorTypes.SqlException
    is JsonParseException -> ErrorTypes.JsonException
    else -> ErrorTypes.Generic(this.message)
}