package uz.practice.controller;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import uz.practice.dto.CodeMessage;
import uz.practice.enums.CodeMessageType;
import uz.practice.util.InlineButtonUtil;

import java.io.File;

public class GeneralController {
    public CodeMessage handle(String text, Long chatId, Integer messageId) {
        CodeMessage codeMessage = new CodeMessage();

        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(chatId);
        sendMessage.setParseMode("Markdown");

        codeMessage.setSendMessage(sendMessage);

        switch (text) {
            case "/start" -> {
                sendMessage.setText("Assalomu alaykum *Todo List* botiga xush kelibsiz!");
                sendMessage.setReplyMarkup(InlineButtonUtil.getKeyboard(InlineButtonUtil.getCollection(
                        InlineButtonUtil.getRow(
                                InlineButtonUtil.getButton("Go to Menu", "menu")
                        )
                )));
                codeMessage.setType(CodeMessageType.MESSAGE);
            }
            case "/help" -> {
                String msg = "*TodoList* Yordam oynasi.\n Siz bu bo'tda qilish kerak bo'lgna islariz jadvalini tuzishingiz mumkin.\n" +
                        "malumot uchun videoni [inline URL](https://www.youtube.com/channel/UCFoy0KOV9sihL61PJSh7x1g)  ko'ring.\n"
                        + "Yokiy manabu videoni ko'ring ";
                sendMessage.setText(msg);
                sendMessage.disableWebPagePreview();

                SendVideo sendVideo = new SendVideo();
                sendVideo.setChatId(chatId);
                File file = new File("files/test_video.mp4");
                sendVideo.setVideo(new InputFile(file, "video.mp4"));
                sendVideo.setCaption("Test uchun video!");
                codeMessage.setSendVideo(sendVideo);

                codeMessage.setType(CodeMessageType.MESSAGE_VIDEO);
            }
            case "/settings" -> {
                sendMessage.setText("Assosiy Nastroykalar!");
                codeMessage.setType(CodeMessageType.MESSAGE);
            }
            case "menu" -> {
                EditMessageText editMessageText = new EditMessageText();
                editMessageText.setText("Assosiy Menyu");
                editMessageText.setChatId(chatId);
                editMessageText.setMessageId(messageId);
                editMessageText.setReplyMarkup(
                        InlineButtonUtil.getKeyboard(
                                InlineButtonUtil.getCollection(
                                        InlineButtonUtil.getRow(
                                                InlineButtonUtil.getButton("Todolar ro'yxati", "/todo/list")
                                        ),
                                        InlineButtonUtil.getRow(
                                                InlineButtonUtil.getButton("Yangi todo yaratish", "/todo/create")
                                        )
                                )
                        )
                );
                codeMessage.setEditMessageText(editMessageText);
                codeMessage.setType(CodeMessageType.EDIT);
            }
        }

        return codeMessage;
    }
}
