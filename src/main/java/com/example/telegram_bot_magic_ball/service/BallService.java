package com.example.telegram_bot_magic_ball.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BallService {

    public static Map<Integer, String> answers = new HashMap();

    static {
        answers.put(1, "Бесспорно");
        answers.put(2, "Предрешено");
        answers.put(3, "Никаких сомнений");
        answers.put(4, "Определённо да");
        answers.put(5, "Можешь быть уверен в этом");
        answers.put(6, "Мне кажется — «да»");
        answers.put(7, "Вероятнее всего");
        answers.put(8, "Хорошие перспективы");
        answers.put(9, "Знаки говорят — «да»");
        answers.put(10, "Пока не ясно, попробуй снова");
        answers.put(11, "Спроси позже");
        answers.put(12, "Лучше не рассказывать");
        answers.put(13, "Сейчас нельзя предсказать");
        answers.put(14, "Сконцентрируйся и спроси опять");
        answers.put(15, "Даже не думай");
        answers.put(16, "Мой ответ — «нет»");
        answers.put(17, "По моим данным — «нет»");
        answers.put(18, "Перспективы не очень хорошие");
        answers.put(19, "Весьма сомнительно");
    }

    public static String getAnswer(){
        return answers.get(getRandomNumberUsingInts(1, 20));
    }

    public static int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

}
