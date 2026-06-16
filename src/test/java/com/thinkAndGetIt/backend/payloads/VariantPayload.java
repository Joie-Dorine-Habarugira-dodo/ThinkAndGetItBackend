package com.thinkAndGetIt.backend.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VariantPayload {
    private String size;
    private String color;
    private String colorHex;
    private String sku;
    private int stock;
    private Float price;
}
