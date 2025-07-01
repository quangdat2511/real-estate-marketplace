package com.javaweb.model.response;

import com.javaweb.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponseDTO extends AbstractDTO {
    String note;
}
