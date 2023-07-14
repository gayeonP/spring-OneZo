package org.kakao.kakaoshopping.utils;

import java.util.function.Supplier;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public class CommonUtils {

	public static BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
		try {
			return new BooleanBuilder(f.get());
		} catch (IllegalArgumentException e) {
			return new BooleanBuilder();
		}
	}
}
