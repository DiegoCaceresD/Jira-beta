package com.proyecto.jirabeta.DTOs;

public class ResponseDTO {
    private boolean success;
    private String errorMsg;
    private Integer errorCode;
    private Object data;

    public ResponseDTO() {
    }
    public ResponseDTO(boolean success, String errorMsg, Integer errorCode, Object data) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "success=" + success +
                ", errorMsg='" + errorMsg + '\'' +
                ", errorCode=" + errorCode +
                ", data=" + data +
                '}';
    }
}
