package com.example.testkiemthu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain object representing a Book (Sách).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sach {
    private String maSach;
    private String tenSach;
    private String tacGia;
    private int namXuatBan;
    private float gia;
} 