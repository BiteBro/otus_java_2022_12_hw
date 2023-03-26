package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.*;
import java.util.stream.Collectors;

public class ProcessorAggregator implements Processor {

    @Override   //группирует выходящий список по name, при этом суммирует поля value
    public Map<String, Double> process(List<Measurement> data) {

        return data.stream().sorted(Comparator.comparing(Measurement::getName))
                .collect(Collectors.groupingBy(
                        Measurement::getName, LinkedHashMap::new,
                        Collectors.summingDouble(Measurement::getValue)));
    }
}
