package app.planet.api.chat;

import app.planet.application.MessageApplicationService;
import app.planet.domain.model.message.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatAPI {
    private final MessageApplicationService messageApplicationService;

    public ChatAPI(MessageApplicationService messageApplicationService) {
        this.messageApplicationService = messageApplicationService;
    }

    @PostMapping("/messageRecord")
    public List<Message> Record(@RequestParam("from") Long from, @RequestParam("to")Long to){
        return messageApplicationService.getMessageRecord(from,to);
    }
}
