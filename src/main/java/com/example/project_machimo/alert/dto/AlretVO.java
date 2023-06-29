package com.example.project_machimo.alert.dto;

import java.sql.Timestamp;

public record AlretVO(
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
