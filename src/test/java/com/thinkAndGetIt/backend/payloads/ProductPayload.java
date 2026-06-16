package com.thinkAndGetIt.backend.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPayload {
    private String name;
    private String description;
    private Float price;
    private Float comparePrice;
    private String categoryId;
    private List<String> tags;
    private boolean isFeatured;
    private boolean isFlashSale;
    private Float flashSalePrice;
    private List<VariantPayload> variants;
}
