package uz.practice.controller;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.practice.dto.CodeMessage;
import uz.practice.service.FileInfoService;

public class MainController extends TelegramLongPollingBot {

    private final GeneralController generalController;
    private final FileInfoService fileInfoService;

    public MainController() {
        this.generalController = new GeneralController();
        this.fileInfoService = new FileInfoService();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            Message message = callbackQuery.getMessage();

            switch (data) {
                case "menu" -> {
                    this.sendMsg(generalController.handle(data, message.getChatId(), message.getMessageId()));
                }
            }
        } else if (update.hasMessage()){
            Message message = update.getMessage();
            String text = message.getText();

            if (text != null) {
                if (text.equals("/start") || text.equals("/help") || text.equals("/settings")) {
                    this.sendMsg(generalController.handle(text, message.getChatId(), message.getMessageId()));
                } else {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(message.getChatId());
                    sendMessage.setText("Bunaqa kamanda yoq!");
                    this.sendMsg(sendMessage);
                }
            } else {
                this.sendMsg(fileInfoService.getFileInfo(message));
            }
        }
    }

    public void sendMsg(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(CodeMessage codeMessage) {
        try {
            switch (codeMessage.getType()) {
                case MESSAGE -> execute(codeMessage.getSendMessage());
                case EDIT -> execute(codeMessage.getEditMessageText());
                case MESSAGE_VIDEO -> {
                    execute(codeMessage.getSendMessage());
                    execute(codeMessage.getSendVideo());
                }
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "test_todo_list_bot";
    }

    @Override
    public String getBotToken() {
        return "6364396169:AAHIahUwy0gqUNnlCY6BnpIaZ04Ny-GyBDI";
    }
}
