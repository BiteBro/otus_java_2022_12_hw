package ru.otus.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import ru.otus.domain.Message;
import ru.otus.domain.MessageDto;
import ru.otus.service.DataStore;

import java.util.Objects;

@RestController
public class DataController {
    private static final Logger log = LoggerFactory.getLogger(DataController.class);
    private static final String ADMIN_ROOM = "1408";
    private final DataStore dataStore;
    private final Scheduler workerPool;

    public DataController(DataStore dataStore, Scheduler workerPool) {
        this.dataStore = dataStore;
        this.workerPool = workerPool;
    }

    @PostMapping(value = "/msg/{roomId}")
    public Mono<Long> messageFromChat(@PathVariable("roomId") String roomId,
                                      @RequestBody MessageDto messageDto) {
        var messageStr = messageDto.messageStr();

        var msgId = Mono.just(new Message(roomId, messageStr))
                .doOnNext(msg -> log.info("messageFromChat:{}", msg))
                .flatMap(dataStore::saveMessage)
                .publishOn(workerPool)
                .doOnNext(msgSaved -> log.info("msgSaved id:{}", msgSaved.getId()))
                .map(Message::getId)
                .subscribeOn(workerPool);

        log.info("messageFromChat, roomId:{}, msg:{} done", roomId, messageStr);
        return msgId;
    }

    @GetMapping(value = "/msg/{roomId}", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<MessageDto> getMessagesByRoomId(@PathVariable("roomId") String roomId) {
        if (Objects.equals(roomId, ADMIN_ROOM)){
            return getMessagesFromAllRoom();
        }else {
            return getMessages(roomId);
        }
    }

    private Flux<MessageDto> getMessagesFromAllRoom(){
        log.info("Connection admin room {}", ADMIN_ROOM);
        return Flux.from(dataStore.loadAllMessages())
                .map(message -> new MessageDto(message.getMsgText()))
                .doOnNext(msgDto -> log.info("msgDto:{}", msgDto))
                .subscribeOn(workerPool);
    }
    private Flux<MessageDto> getMessages(String roomId){
        log.info("Connection room {}", roomId);
        return Mono.just(roomId)
                .doOnNext(room -> log.info("getMessagesByRoomId, room:{}", room))
                .flatMapMany(dataStore::loadMessages)
                .map(message -> new MessageDto(message.getMsgText()))
                .doOnNext(msgDto -> log.info("msgDto:{}", msgDto))
                .subscribeOn(workerPool);
    }
}
