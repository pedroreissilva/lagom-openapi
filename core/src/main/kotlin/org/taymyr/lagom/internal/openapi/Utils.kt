@file:JvmName("Utils")

package org.taymyr.lagom.internal.openapi

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import java.io.IOException

private val jsonMapper = ObjectMapper()
private val yamlMapper = ObjectMapper(YAMLFactory())

fun yamlToJson(yaml: String?, mapper: ObjectMapper?): String? = try {
    yaml?.let {
        mapper?.let {
            mapper.writeValueAsString(yamlMapper.readValue(yaml, Any::class.java))
        } ?: run {
            jsonMapper.writeValueAsString(yamlMapper.readValue(it, Any::class.java))
        }
    }
} catch (e: IOException) {
    null
}

fun jsonToYaml(json: String?, mapper: ObjectMapper?): String? = try {
    json?.let {
        mapper?.let {
            mapper.writeValueAsString(jsonMapper.readValue(json, Any::class.java))
        } ?: run {
            yamlMapper.writeValueAsString(jsonMapper.readValue(it, Any::class.java))
        }
    }
} catch (e: IOException) {
    null
}
