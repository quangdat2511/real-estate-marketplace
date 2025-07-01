package com.javaweb.service;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.GuestDTO;

public interface GuestService {
    CustomerEntity createGuest(GuestDTO guestDTO);
}
