package com.szs.test.exception;

import com.szs.test.cd.ErrorCd;
import com.szs.test.dto.ResDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * BindingResult에 대한 예외처리
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("[MethodArgumentNotValidException] cause: {}, message: {}",NestedExceptionUtils.getMostSpecificCause(ex),ex.getMessage());
        BindingResult bindingResult = ex.getBindingResult();

        String errorMessage = bindingResult.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("\n"));

        ResDTO response = new ResDTO(ErrorCd.INVALID_PARAMETER.getErrorCd(), errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 잘못된 타입에 대한 예외처리
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("[HttpMessageNotReadableException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(ex),ex.getMessage());
        return new ResponseEntity<>(new ResDTO(ErrorCd.INVALID_JSON_FORMAT.getErrorCd(), ErrorCd.INVALID_JSON_FORMAT.getErrorMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 만료된 토큰에 대한 예외처리
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ExpiredAccessTokenException.class)
    public ResponseEntity<ResDTO> handleExpiredAccessTokenException(ExpiredAccessTokenException ex) {
        log.error("[ExpiredAccessTokenException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(ex),ex.getMessage());
        return new ResponseEntity<>(new ResDTO(ErrorCd.EXPIRED_ACCESS_TOKEN.getErrorCd(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 유효하지 않은 토큰에 대한 예외처리
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(InvalidAccessTokenException.class)
    public ResponseEntity<ResDTO> handleInvalidAccessTokenException(InvalidAccessTokenException ex) {
        log.error("[InvalidAccessTokenException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(ex),ex.getMessage());
        return new ResponseEntity<>(new ResDTO(ErrorCd.INVALID_JSON_FORMAT.getErrorCd(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 유효하지 않은 토큰에 대한 예외처리
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ResDTO> handleInvalidPasswordException(InvalidPasswordException ex) {
        log.error("[InvalidPasswordException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(ex),ex.getMessage());
        return new ResponseEntity<>(new ResDTO(ErrorCd.INVALID_PASSWORD.getErrorCd(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 데이터가 존재하지 않을 경우 예외처리
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NotExistsDataException.class)
    public ResponseEntity<ResDTO> handleNotExistsDataException(NotExistsDataException ex) {
        log.error("[NotExistsDataException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(ex),ex.getMessage());
        return new ResponseEntity<>(new ResDTO(ErrorCd.NOT_EXISTS_DATA.getErrorCd(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 가입할 수 없는 유저 정보에 대한 에러 처리
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NotSignupUserException.class)
    public ResponseEntity<ResDTO> handleNotSignupUserException(NotSignupUserException ex){
        log.error("[NotSignupUserException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(ex),ex.getMessage());
        return new ResponseEntity<>(new ResDTO(ErrorCd.NOT_SIGNUP_USER.getErrorCd(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 서버에 대한 에러 예외처리
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResDTO> handleException(Exception ex) {
        log.error("[Exception] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(ex),ex.getMessage());
        return new ResponseEntity<>(new ResDTO(ErrorCd.INTERNAL_SERVER_ERROR.getErrorCd(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
