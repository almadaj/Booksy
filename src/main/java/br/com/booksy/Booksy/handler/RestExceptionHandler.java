package br.com.booksy.Booksy.handler;

import br.com.booksy.Booksy.domain.dto.MessageDTO;
import br.com.booksy.Booksy.exception.CommonException;
import br.com.booksy.Booksy.exception.ValidationExceptionDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> fieldErrorsMap = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (msg1, msg2) -> msg1));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Invalid fields")
                        .fieldErrors(fieldErrorsMap)
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

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
                        "booksy.global.handler.exception"));
    }
}
