package com.example.employee.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //là annotation tổng hợp, tương đương với việc Lombok tự sinh ra:
@Builder //tạo ra Builder Pattern, giúp khởi tạo object dễ đọc và an toàn:
@NoArgsConstructor //onstructor không tham số:
@AllArgsConstructor //Tạo constructor đầy đủ tham số:
public class UserRequestDTO {
    private  String name ;
    private  String email ;
    private String phone ;
    private  String pass;


}
