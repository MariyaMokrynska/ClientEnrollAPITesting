package com.clients.utilities;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class PriceUtils {

    /**
     * Checks if the prices list is valid (not null and not empty).
     *
     * @param prices the list of prices to check
     * @return true if the prices list is valid, false otherwise
     */
    public static boolean arePricesValid(List<Map<String, Object>> prices) {
        return prices != null && !prices.isEmpty();
    }

    /**
     * Retrieves the priceId associated with the given payment type from the prices list.
     *
     * @param prices the list of prices to search
     * @param paymentType the payment type to find the associated priceId for
     * @return the priceId associated with the payment type, or null if not found
     */
    public static String getPriceIdForPaymentType(List<Map<String, Object>> prices, String paymentType) {
        for (Map<String, Object> price : prices) {
            if (price.get("type").equals(paymentType)) {
                return (String) price.get("priceId");
            }
        }
        return null;
    }

    /**
     * Logs the found priceId for the specified payment type.
     *
     * @param selectedPriceId the priceId found for the payment type
     * @param paymentType the payment type associated with the priceId
     */
    public static void logPriceId(String selectedPriceId, String paymentType) {
        if (selectedPriceId != null) {
            System.out.println("Found priceId for payment type '" + paymentType + "': " + selectedPriceId);
        } else {
            System.out.println("No priceId found for payment type '" + paymentType + "'.");
        }
    }

    /**
     * Logs response details including the status code and response time.
     *
     * @param response the response to log details from
     */
    public static void logResponseDetails(Response response) {
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Time: " + response.getTime() + " ms");
    }
}
