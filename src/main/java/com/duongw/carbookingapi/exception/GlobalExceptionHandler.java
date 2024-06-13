package com.duongw.carbookingapi.exception;

import com.duongw.carbookingapi.DTO.ErrorDetail;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler là một Controller Advice chịu trách nhiệm xử lý toàn bộ các exception trong ứng dụng.
 * Nó cung cấp các phương thức để xử lý các loại exception cụ thể và trả về ResponseEntity chứa thông tin lỗi và mã trạng thái HTTP tương ứng.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Xử lý exception khi không tìm thấy resource.
     *
     * @param exception ResourceNotFoundException được ném ra khi không tìm thấy resource.
     * @param request   WebRequest chứa thông tin về request từ client.
     * @return ResponseEntity chứa thông tin chi tiết lỗi và mã trạng thái HTTP 404 (NOT_FOUND).
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        ErrorDetail detail = new ErrorDetail(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
    }

    /**
     * Xử lý exception khi có lỗi trong quá trình thực hiện các hành động liên quan đến đặt xe (CarBookingException).
     *
     * @param exception CarBookingException được ném ra khi có lỗi trong quá trình đặt xe.
     * @param request   WebRequest chứa thông tin về request từ client.
     * @return ResponseEntity chứa thông tin chi tiết lỗi và mã trạng thái HTTP 400 (BAD_REQUEST).
     */
    @ExceptionHandler(CarBookingException.class)
    public ResponseEntity<ErrorDetail> handleCarBookingException(CarBookingException exception, WebRequest request) {
        ErrorDetail detail = new ErrorDetail(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detail, HttpStatus.BAD_REQUEST);
    }

    /**
     * Xử lý exception chung (Exception).
     *
     * @param exception Exception là exception chung được ném ra khi có lỗi không xác định trong ứng dụng.
     * @param request   WebRequest chứa thông tin về request từ client.
     * @return ResponseEntity chứa thông tin chi tiết lỗi và mã trạng thái HTTP 500 (INTERNAL_SERVER_ERROR).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleGlobalException(Exception exception, WebRequest request) {
        ErrorDetail detail = new ErrorDetail(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Xử lý exception khi dữ liệu đầu vào không hợp lệ (MethodArgumentNotValidException).
     *
     * @param ex      MethodArgumentNotValidException được ném ra khi dữ liệu đầu vào không hợp lệ.
     * @param headers HttpHeaders của response.
     * @param status  HttpStatus của response.
     * @param request WebRequest chứa thông tin về request từ client.
     * @return ResponseEntity chứa danh sách lỗi và mã trạng thái HTTP 400 (BAD_REQUEST).
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
