/*
 * Copyright 2013 Nicolas Morel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.nmorel.gwtjackson.guava.client.deser;

import java.io.IOException;

import com.github.nmorel.gwtjackson.client.JsonDeserializationContext;
import com.github.nmorel.gwtjackson.client.JsonDeserializer;
import com.github.nmorel.gwtjackson.client.JsonDeserializerParameters;
import com.github.nmorel.gwtjackson.client.deser.map.BaseMapJsonDeserializer;
import com.github.nmorel.gwtjackson.client.deser.map.key.KeyDeserializer;
import com.github.nmorel.gwtjackson.client.stream.JsonReader;
import com.google.common.collect.ImmutableSortedMap;

/**
 * Default {@link JsonDeserializer} implementation for {@link ImmutableSortedMap}.
 * <p>Cannot be overriden. Use {@link BaseMapJsonDeserializer}.</p>
 *
 * @param <K> Type of the keys inside the {@link ImmutableSortedMap}
 * @param <V> Type of the values inside the {@link ImmutableSortedMap}
 *
 * @author Nicolas Morel
 */
public final class ImmutableSortedMapJsonDeserializer<K extends Comparable<?>, V> extends BaseImmutableMapJsonDeserializer<ImmutableSortedMap<K, V>, K, V> {

    /**
     * @param keyDeserializer {@link KeyDeserializer} used to deserialize the keys.
     * @param valueDeserializer {@link JsonDeserializer} used to deserialize the values.
     * @param <K> Type of the keys inside the {@link ImmutableSortedMap}
     * @param <V> Type of the values inside the {@link ImmutableSortedMap}
     *
     * @return a new instance of {@link ImmutableSortedMapJsonDeserializer}
     */
    public static <K extends Comparable<?>, V> ImmutableSortedMapJsonDeserializer<K, V> newInstance( KeyDeserializer<K> keyDeserializer,
                                                                               JsonDeserializer<V> valueDeserializer ) {
        return new ImmutableSortedMapJsonDeserializer<K, V>( keyDeserializer, valueDeserializer );
    }

    /**
     * @param keyDeserializer {@link KeyDeserializer} used to deserialize the keys.
     * @param valueDeserializer {@link JsonDeserializer} used to deserialize the values.
     */
    private ImmutableSortedMapJsonDeserializer( KeyDeserializer<K> keyDeserializer, JsonDeserializer<V> valueDeserializer ) {
        super( keyDeserializer, valueDeserializer );
    }

    @Override
    protected ImmutableSortedMap<K, V> doDeserialize( JsonReader reader, JsonDeserializationContext ctx,
                                                      JsonDeserializerParameters params ) throws IOException {
        ImmutableSortedMap.Builder<K, V> builder = ImmutableSortedMap.naturalOrder();
        buildMap( reader, ctx, params, builder );
        return builder.build();
    }
}
