package com.example.backend_tutorial.model;

public enum PaymentStatus {
    PENDING,    // Đang chờ xử lý
    SUCCESS,    // Thanh toán thành công
    FAILED,     // Thanh toán thất bại
    CANCELLED,  // Đã hủy thanh toán
    REFUNDED    // Hoàn tiền
}
