package com.fsc.authorization.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserInfoDto(
    String certno,
    String name,
    String mail,
    String pid,
    @JsonProperty("pid_issuer") String pidIssuer,
    String egn,
    String organization,
    String org,
    @JsonProperty("org_issuer") String orgIssuer,
    String bulstat,
    @JsonProperty("private_key") String privateKey,
    String certificate
) {}
