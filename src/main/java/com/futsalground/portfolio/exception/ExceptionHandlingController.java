package com.futsalground.portfolio.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(MemberNotFoundException.class)
    public String memberNotFoundError()
    {
        return "error/memberNotFound";
    }

    @ExceptionHandler(BoardNotFoundException.class)
    public String boardNotFoundError(Exception e)
    {
        return "error/boardNotFound";
    }

    @ExceptionHandler(GroundNotFoundException.class)
    public String groundNotFoundError(Exception e)
    {
        return "error/groundNotFound";
    }

    @ExceptionHandler(RecruitNotFoundException.class)
    public String recruitNotFoundError(Exception e)
    {
        return "error/recruitNotFound";
    }

    @ExceptionHandler(UserNameDuplicateException.class)
    public String userNameDuplicateError(Exception e)
    {
        return "error/userNameDuplicate";
    }

    @ExceptionHandler(ApplyMemberNotFoundException.class)
    public String applyMemberNotFoundError(Exception e)
    {
        return "error/applyMemberNotFound";
    }
}