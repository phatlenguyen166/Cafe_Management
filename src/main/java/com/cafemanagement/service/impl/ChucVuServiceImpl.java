package com.cafemanagement.service.impl;


import com.cafemanagement.model.ChucVu;
import com.cafemanagement.repository.ChucVuRepository;
import com.cafemanagement.service.ChucVuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChucVuServiceImpl implements ChucVuService {

    private final ChucVuRepository chucVuRepository;

    @Override
    public List<ChucVu> getListChucVu() {
        return chucVuRepository.findAll();
    }

}
