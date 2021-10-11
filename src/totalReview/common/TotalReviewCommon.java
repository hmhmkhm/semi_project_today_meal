package totalReview.common;

import java.util.HashMap;
import java.util.Map;

public class TotalReviewCommon {
	public static final Map<String, Integer> CATEGORY_MAP = new HashMap<>();

	static {
		CATEGORY_MAP.put("korean_food", 1);
		CATEGORY_MAP.put("american_food", 2);
		CATEGORY_MAP.put("chinese_food", 3);
		CATEGORY_MAP.put("japanese_food", 4);
		CATEGORY_MAP.put("snack", 5);
		CATEGORY_MAP.put("midnight_snack", 6);
		CATEGORY_MAP.put("salad", 7);
	}
}
