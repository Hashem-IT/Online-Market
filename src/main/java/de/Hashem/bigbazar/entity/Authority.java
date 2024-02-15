package de.Hashem.bigbazar.entity;

import org.springframework.security.core.GrantedAuthority;

/*
* Klasse für Authority 
*/

public class Authority implements GrantedAuthority {
    private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
