package com.fsc.authorization.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExternalTokenDto(
    @JsonProperty("access_token") String token,
    @JsonProperty("expires_in") int expiresInSec // probably seconds
) {}
