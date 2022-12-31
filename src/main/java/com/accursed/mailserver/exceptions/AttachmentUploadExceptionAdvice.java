package com.accursed.mailserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AttachmentUploadExceptionAdvice extends ResponseEntityExceptionHandler {

    public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException exc){
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Max file size exceeded");
    }
}
