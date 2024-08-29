package com.lab4dx.s03_jdbc_di.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter // 컴파일시 lombok이 get, set을 정의해줌
@ToString
public class Customer {
    private int customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
}
