package com.dxy.data;

import com.dxy.anotations.Column;
import com.dxy.anotations.Table;
import lombok.Data;

@Data
@Table(name = "users") // 指定表名
public class User {
    @Column(name = "id", type = "BIGINT", primaryKey = true, autoIncrement = true)
    private Long id;
    @Column( type = "VARCHAR(50)", nullable = false)
    private String 名字;
    @Column(name = "email", type = "VARCHAR(100)", nullable = false)
    private String email;
    @Column(name = "created_at", type = "TIMESTAMP")
    private java.util.Date createTime;
}
