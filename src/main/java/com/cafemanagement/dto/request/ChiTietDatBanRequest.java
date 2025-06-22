package com.cafemanagement.dto.request;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietDatBanRequest {
    private Integer maBan;
    private Integer maDatBan;
    private Integer maNhanVien;
    private Instant ngayGioDat;
    private String sdtKhachHang;
    private String tenKhachHang;

}
