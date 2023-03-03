/*
 * Copyright (c) 2022 GLA Research and Development Directorate
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.grad.secom.core.base;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.grad.secom.core.exceptions.SecomValidationException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.grad.secom.core.base.SecomConstants.SECOM_DATE_TIME_FORMAT;
import static org.grad.secom.core.base.SecomConstants.SECOM_DATE_TIME_FORMATTER;

/**
 * The DateTimeDeSerializer Class
 * <p/>
 * In SECOM the date-time format is not the frequently used ISO. According to
 * the standard A DateTime is a combination of a date and a time type.
 * Character encoding of a DateTime shall follow the example:
 * EXAMPLE: 19850412T101530
 *
 * @author Nikolaos Vastardis (email: Nikolaos.Vastardis@gla-rad.org)
 */
public class DateTimeDeSerializer extends StdDeserializer<LocalDateTime> {

    /**
     * Instantiates a new Byte array de serializer.
     */
    protected DateTimeDeSerializer() {
        this(null);
    }

    /**
     * Instantiates a new Byte array de serializer.
     *
     * @param t the byte array class
     */
    protected DateTimeDeSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    /**
     * Implements the de-serialization procedure of the de-serializer.
     *
     * @param jp    The JSON Parser
     * @param ctxt  The deserialization context
     * @return the deserialized output
     * @throws IOException for any IO exceptions
     */
    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final String value = jp.getCodec().readValue(jp, String.class);
        if (value == null || value.isEmpty()) return null;
        try {
            return LocalDateTime.parse(value, SECOM_DATE_TIME_FORMATTER);
        } catch (Exception ex) {
            return null;
        }
    }
}
