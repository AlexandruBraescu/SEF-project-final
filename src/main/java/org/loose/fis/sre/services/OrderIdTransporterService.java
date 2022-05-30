package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;

public class OrderIdTransporterService {
    private static NitriteId id;

    public static NitriteId getId() {
        return id;
    }

    public static void setId(NitriteId id) {
        OrderIdTransporterService.id = id;
    }
}
