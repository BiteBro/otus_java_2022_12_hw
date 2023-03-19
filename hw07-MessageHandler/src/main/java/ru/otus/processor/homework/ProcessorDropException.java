package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

import java.time.LocalDateTime;

public class ProcessorDropException implements Processor {
    LocalDateTime time;

    public ProcessorDropException(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public Message process(Message message) {
        if (time.getSecond() % 2 == 0 ){
            throw new RuntimeException(String.valueOf(message.getId()));
        }
        return message;
    }
}
