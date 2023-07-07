package com.example.project_machimo.gyuha.alert.dto;

import java.sql.Timestamp;

public record AlertVO(
        Integer alId
        , Integer userId
        , Integer productId
        , Integer alCount
        , Byte checkedFlag
        , String pName
        , String alMessage
        , Timestamp alCreateAt
        ) {
}
