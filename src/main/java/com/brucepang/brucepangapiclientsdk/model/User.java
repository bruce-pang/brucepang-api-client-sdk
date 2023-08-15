package com.brucepang.brucepangapiclientsdk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 用户实体类
 * @author BrucePang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String username;
}
