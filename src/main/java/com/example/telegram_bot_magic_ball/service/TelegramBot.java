package com.example.telegram_bot_magic_ball.service;

import com.example.telegram_bot_magic_ball.config.BotConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String answer = "";

        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText){
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    try {
                        answer = BallService.getAnswer();

                    } catch (Exception e) {
                        sendMessage(chatId, "Что-то пошло не так..." + "\n" +
                                "Задай вопрос еще раз.");
                    }
                    sendMessage(chatId, answer);
                    continueCommandReceived(chatId);
            }
        }

    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = "Привет, " + name + ", рад тебя приветствовать!" + "\n" +
                "Я волшебный шар и отвечу на любой твой вопрос!" + "\n" +
                "Задай свой вопрос:";
        sendMessage(chatId, answer);
    }

    private void continueCommandReceived(Long chatId) {
        String answer = "Задай еще вопрос!";
        sendMessage(chatId, answer);
    }

    private void sendMessage(Long chatId, String textToSend){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }
    }

}
