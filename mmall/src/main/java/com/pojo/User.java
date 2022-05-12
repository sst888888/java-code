package com.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description
 *
 * @Author Chaiphat
 * @Date 2020/10/18 17:50
 * @Version 1.0
 **/
@Data
public class User implements Serializable {

    private Long id;
    private String name;
}
