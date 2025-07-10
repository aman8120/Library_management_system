package com.example.lib_mng_sys.dto.request;


import com.example.lib_mng_sys.dto.rules.Email;
import com.example.lib_mng_sys.dto.rules.OnlyAlphabetsAndUnderScores;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @OnlyAlphabetsAndUnderScores
    private String userName;

    @Email
    private String email;

    @NotNull(message = "Phone number cannot be Null")
    @NotBlank(message = "Phone number cannot be Blanks")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must contain only digits and be exactly 10 digits long")
    private String phNo;


}
