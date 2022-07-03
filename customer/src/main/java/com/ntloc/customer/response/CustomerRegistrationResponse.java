package com.ntloc.customer.response;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRegistrationResponse {

    private Long id;
    private String name;
    private String email;
}
