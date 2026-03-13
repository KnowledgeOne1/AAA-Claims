package com.aaa.service.claim.util;

import java.util.UUID;

public class Utils {

	public static String generateUUIDString() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
