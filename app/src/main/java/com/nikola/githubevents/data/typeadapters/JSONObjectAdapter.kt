package com.nikola.githubevents.data.typeadapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import okio.Buffer
import org.json.JSONException
import org.json.JSONObject

class JSONObjectAdapter {

    @FromJson
    fun fromJson(reader: JsonReader): JSONObject? {
        return (reader.readJsonValue() as? Map<String, Any>)?.let { data ->
            try {
                JSONObject(data)
            } catch (e: JSONException) {
                return null
            }
        }
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: JSONObject?) {
        if (value != null) {
            writer.value(Buffer().writeUtf8(value.toString()))
        } else {
            writer.value(null as String?)
        }
    }

}