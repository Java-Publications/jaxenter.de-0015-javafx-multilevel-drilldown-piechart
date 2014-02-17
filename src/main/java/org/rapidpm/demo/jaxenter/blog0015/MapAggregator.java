package org.rapidpm.demo.jaxenter.blog0015;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;

/**
 * Created by ts40 on 17.02.14.
 */
@FunctionalInterface
public interface MapAggregator <T, K> {

    public K getAggregationKey(T value);

    public default Map<K, List<T>> aggregate(final Collection<T> dataCollection) {
        return dataCollection.stream()
                .collect(groupingBy(this::getAggregationKey));
    }
}
