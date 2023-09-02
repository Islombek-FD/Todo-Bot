package uz.practice.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Video;
import uz.practice.dto.CodeMessage;
import uz.practice.enums.CodeMessageType;

import java.util.List;

public class FileInfoService {

    public CodeMessage getFileInfo(Message message) {
        Long cId = message.getChatId();

        CodeMessage codeMessage = new CodeMessage();
        codeMessage.setType(CodeMessageType.MESSAGE);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(cId);


        if (message.getPhoto() != null) {
            String s = this.showPhotoDetail(message.getPhoto());
            sendMessage.setText(s);
        } else if (message.getVideo() != null) {
            String s = this.showVideoDetail(message.getVideo());
            sendMessage.setText(s);
        } else {
            sendMessage.setText("NOT FOUND");
        }

        codeMessage.setSendMessage(sendMessage);
        return codeMessage;
    }

    private String showPhotoDetail(List<PhotoSize> photoSizeList) {
        StringBuilder s = new StringBuilder("------------------------- PHOTO INFO -------------------------\n");
        for (PhotoSize photoSize : photoSizeList) {
            s.append(" Size = ").append(photoSize.getFileSize()).append("  ,    ID  = ").append(photoSize.getFileId()).append(" \n");
        }
        return s.toString();
    }

    private String showVideoDetail(Video video) {
        String s = "------------------------- VIDEO INFO -------------------------\n";
        s += video.toString();
        return s;
    }
}
