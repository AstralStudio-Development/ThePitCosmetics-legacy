package cn.starry.cosmetics.api.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortedType {
    LOWEST_FIRST(0, "最低稀有度优先"),
    HIGHEST_FIRST(1, "最高稀有度优先");

    private final int id;
    private final String displayName;
}
