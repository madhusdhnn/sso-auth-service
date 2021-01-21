package com.thetechmaddy.authservice.messaging.models;

import com.thetechmaddy.authservice.models.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.regex.Pattern;

import static com.thetechmaddy.authservice.utils.RegexPatternUtils.B_CRYPT_PATTERN_STRING;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail {

    private String userId;
    private String companyId;
    private String organizationId;
    private String username;
    private String password;
    private String mobileNumber;
    private String emailId;
    private String roleId;

    public boolean validatePassword() {
        Pattern bCryptPattern = Pattern.compile(B_CRYPT_PATTERN_STRING);
        return bCryptPattern.matcher(this.password).matches();
    }

    public RoleType parseRole() {
        return RoleType.parse(this.roleId);
    }

}
