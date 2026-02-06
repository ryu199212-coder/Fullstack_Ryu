package ex006.dto;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ExDto {                 
    private int appUserId; 
    private String email;
    private String password;
    private int mbtiTypeId;                            
    private String login;                       
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt; 
    private int join;
    private String ufile;
    
}
