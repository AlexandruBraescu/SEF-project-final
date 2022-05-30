package org.loose.fis.sre.services;

import org.dizitart.no2.NitriteId;

public class ProblemTransportService {
    private static NitriteId productId;

    public static NitriteId getProductId() {
        return productId;
    }

    public static void setProductId(NitriteId productId) {
        ProblemTransportService.productId = productId;
    }
}
