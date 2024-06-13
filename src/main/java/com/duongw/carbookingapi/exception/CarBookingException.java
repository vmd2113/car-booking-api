package com.duongw.carbookingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * CarBookingException là một ngoại lệ được đặt ra để xử lý trường hợp không tìm thấy thông tin về đặt xe.
 * Ngoại lệ này được ánh xạ với mã trạng thái HTTP 404 (NOT_FOUND).
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CarBookingException extends RuntimeException {

    // Trạng thái HTTP và thông điệp lỗi
    private HttpStatus status;
    private String message;

    /**
     * Constructor với trạng thái HTTP và thông điệp lỗi.
     *
     * @param status  Trạng thái HTTP
     * @param message Thông điệp lỗi
     */
    public CarBookingException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Constructor với trạng thái HTTP, thông điệp lỗi và một thông điệp ngoại lệ chính.
     *
     * @param status   Trạng thái HTTP
     * @param message  Thông điệp lỗi
     * @param message1 Thông điệp ngoại lệ chính
     */
    public CarBookingException(HttpStatus status, String message, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    /**
     * Phương thức getter cho trạng thái HTTP.
     *
     * @return Trạng thái HTTP
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Phương thức setter cho trạng thái HTTP.
     *
     * @param status Trạng thái HTTP
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /**
     * Phương thức getter cho thông điệp lỗi.
     *
     * @return Thông điệp lỗi
     */
    public String getMessage() {
        return message;
    }

    /**
     * Phương thức setter cho thông điệp lỗi.
     *
     * @param message Thông điệp lỗi
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
