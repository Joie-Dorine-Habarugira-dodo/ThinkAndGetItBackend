package com.thinkAndGetIt.backend.flow;

import com.thinkAndGetIt.backend.base.RestResource;
import com.thinkAndGetIt.backend.constants.Routes;
import com.thinkAndGetIt.backend.payloads.ProductPayload;
import com.thinkAndGetIt.backend.payloads.VariantPayload;
import com.thinkAndGetIt.backend.tokenManager.TokenManager;
import com.thinkAndGetIt.backend.utils.ConfigLoader;
import com.thinkAndGetIt.backend.utils.PayloadFaker;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductFlow {

    public Response getAllProducts() throws IOException {
        return RestResource.get(Routes.PRODUCTS);
    }

    public static Response createProduct() throws IOException {
        VariantPayload variant = new VariantPayload();

        variant.setSize(PayloadFaker.getSize());
        variant.setColor(PayloadFaker.getColor());
        variant.setColorHex(PayloadFaker.getColorHex());
        variant.setSku(PayloadFaker.getSku());
        variant.setStock(PayloadFaker.getStock());
        variant.setPrice(PayloadFaker.getVariantPrice());

        List<VariantPayload> variants = new ArrayList<>();
        variants.add(variant);

        ProductPayload product = new ProductPayload();

        product.setName(PayloadFaker.generateProductName());
        product.setDescription(PayloadFaker.getDescription());
        product.setPrice(PayloadFaker.getPrice());
        product.setComparePrice(PayloadFaker.getComparePrice());
        product.setCategoryId(ConfigLoader.get("categoryID"));
        product.setTags(PayloadFaker.getTags());
        product.setFeatured(PayloadFaker.isFeatured());
        product.setFlashSale(PayloadFaker.isFlashSale());
        product.setFlashSalePrice(PayloadFaker.getFlashSalePrice());
        product.setVariants(variants);

        return RestResource.post(TokenManager.getAdminAuthToken(), Routes.PRODUCTS, product);
    }

    public Response getProductBySlug(String slug) throws IOException {
        return RestResource.get(Routes.PRODUCT_BY_SLUG, "slug", slug);
    }

    public Response updateProduct(String property, Object value) throws IOException {
        Map<String, Object> payload = Map.of(property, value);
        return RestResource.put(TokenManager.getAdminAuthToken(), Routes.PRODUCT_BY_ID, "id", ConfigLoader.get("productID"), payload);
    }

    public Response deleteProduct(String id) throws IOException {
        return RestResource.delete(TokenManager.getAdminAuthToken(),Routes.PRODUCT_BY_ID, "id", id);
    }

    public Response uploadProductImages() throws IOException {
        File image = PayloadFaker.getProductImage();
        return RestResource.uploadImage(Routes.PRODUCT_IMAGES, "id", ConfigLoader.get("productID"), image);
    }

    public Response getTrendingProducts() throws IOException {
        return RestResource.get(Routes.PRODUCT_TRENDING);
    }

    public Response getFlashSaleProducts() throws IOException {
        return RestResource.get(Routes.PRODUCT_FLASH_SALES);
    }

    public Response getRelatedProducts(String id) throws IOException {
        return RestResource.get(Routes.PRODUCT_RELATED, "id", id);
    }
}

