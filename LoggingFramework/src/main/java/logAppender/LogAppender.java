package logAppender;

import logLevel.LogMessage;

public interface LogAppender {
    void append(LogMessage logMessage);
}
