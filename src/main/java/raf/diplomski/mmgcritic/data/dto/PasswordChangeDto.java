package raf.diplomski.mmgcritic.data.dto;

import lombok.Data;

@Data
public class PasswordChangeDto {
    private String oldPassword;
    private String newPassword;
    private int id;

}
