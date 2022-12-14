package com.freshworks.SpringProject.Exception;

import com.freshworks.SpringProject.Instances.exceptions.*;
import com.freshworks.SpringProject.team.exceptions.TeamNotFoundException;
import com.freshworks.SpringProject.teamMembers.exceptions.TeamMemberNotFount;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomiseResponseEntity extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            TeamNotFoundException.class,
            TeamMemberNotFount.class,
            InstancesNotFoundException.class,
            InstanceNotUnderRequestedTeamException.class,
            NotValidOperationException.class,
            InstanceIsNotInUseException.class,
            InstanceIsInUseException.class
    })
    public final ResponseEntity<ErrorDetails> handleTeamNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
