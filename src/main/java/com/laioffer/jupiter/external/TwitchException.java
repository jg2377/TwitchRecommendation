package com.laioffer.jupiter.external;
// 再跟twitch交互过程中 可能会有各种各样的异常 IOException， HTTPException，JSOnException
// 这里全部归类为 Twitch Exception
// 这里是 TwitchClient 抛出给 servlet的 Exception
public class TwitchException extends RuntimeException {
    public TwitchException(String errorMessage) {
        super(errorMessage);
    }
}
