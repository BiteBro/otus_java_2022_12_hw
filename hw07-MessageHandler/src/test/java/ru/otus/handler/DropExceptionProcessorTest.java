package ru.otus.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.model.Message;
import ru.otus.processor.Processor;
import ru.otus.processor.homework.ProcessorDropException;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

public class DropExceptionProcessorTest {

    private Message message;

    @BeforeEach
    public void init(){
        message = new Message.Builder(1).field1("field_1").field10("field_10").field5("field_5").build();
    }

    @Test
    @DisplayName("Test ProcessorDropException class, method throwing an exception at an even second")
    public void throwingExceptionProcessorTest(){

        Processor throwException = new ProcessorDropException(LocalDateTime.of(
                2023, Month.MARCH,19,20,25,16));

        try{
            throwException.process(message);
            fail();
        }catch (RuntimeException re){
            assertTrue(true);
        }

    }
    @Test
    @DisplayName("Test ProcessorDropException class, method throwing exception at odd second")
    public void nonThrowingExceptionProcessorTest(){

        Processor notTrowException = new ProcessorDropException(LocalDateTime.of(
                2023, Month.MARCH,19,20,27,21));

        try{
            assertEquals(notTrowException.process(message), message);
        }catch (RuntimeException re){
            fail();
        }

    }


}
