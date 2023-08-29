package app.planet.application;


import app.planet.application.command.chat.MessageCommand;
import app.planet.domain.Repository.MessageRepository;
import app.planet.domain.model.message.Message;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageApplicationService {

    @Resource
    private MessageRepository messageRepository;

    //keypoint:获取私聊聊天记录
    public List<Message> getMessageRecord(Long from,Long to) {
        List<Message> messages = new ArrayList<>();
        messages.addAll(messageRepository.findAllByFromAndTo(from,to));
        messages.addAll(messageRepository.findAllByFromAndTo(to,from));
        return messages;
    }
    //keypoint:获取群聊聊天记录
    public List<Message> getChannelMessageRecord(Long from,Long to) {
        return messageRepository.findAllByTo(to);
    }
}
