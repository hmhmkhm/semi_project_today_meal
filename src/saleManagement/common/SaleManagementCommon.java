package saleManagement.common;

import java.util.HashMap;
import java.util.Map;

public class SaleManagementCommon {
	public static final Map<String, Integer> ORDER_STATUS_MAP = new HashMap<>();

	static {
		ORDER_STATUS_MAP.put("default", 0);
		ORDER_STATUS_MAP.put("processing", 1);
		ORDER_STATUS_MAP.put("pickup", 2);
		ORDER_STATUS_MAP.put("transit", 3);
		ORDER_STATUS_MAP.put("delivered", 4);
		ORDER_STATUS_MAP.put("requestCancel", 5);
		ORDER_STATUS_MAP.put("cancelled", 6);
	}
}
