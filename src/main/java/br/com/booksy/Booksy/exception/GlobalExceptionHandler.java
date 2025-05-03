package br.com.booksy.Booksy.exception;

import br.com.booksy.Booksy.domain.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CommonException.class)
    protected ResponseEntity<MessageDTO> handleCommonsException(CommonException exception) {
        logger.error("[exception] {}", String.valueOf(exception));
        return exception.getMessageError();
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<MessageDTO> handleException(Exception ex) {
        logger.error("Exception não tratada: ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageDTO("Exception não tratada: " + ex.toString(),
                        "unichristus.global.handler.exception"));
    }
}