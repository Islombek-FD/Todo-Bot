package uz.practice.dto;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import uz.practice.enums.CodeMessageType;

public class CodeMessage {
    private SendMessage sendMessage;
    private EditMessageText editMessageText;
    private SendVideo sendVideo;

    private CodeMessageType type;

    public SendMessage getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    public EditMessageText getEditMessageText() {
        return editMessageText;
    }

    public void setEditMessageText(EditMessageText editMessageText) {
        this.editMessageText = editMessageText;
    }

    public SendVideo getSendVideo() {
        return sendVideo;
    }

    public void setSendVideo(SendVideo sendVideo) {
        this.sendVideo = sendVideo;
    }

    public CodeMessageType getType() {
        return type;
    }

    public void setType(CodeMessageType type) {
        this.type = type;
    }
}
