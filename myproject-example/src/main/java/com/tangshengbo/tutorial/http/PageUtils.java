package com.tangshengbo.tutorial.http;

/**
 * Created by Tangshengbo
 *
 * @author Tangshengbo
 * @date 2019/9/24
 */
public final class PageUtils {

    private PageUtils() {

    }

    /**
     * 计算总页数
     *
     * @param total    总记录数
     * @param pageSize 页大小
     * @return 总页数
     */
    public static int getTotalPages(int total, int pageSize) {
        return pageSize == 0 ? 1 : (int) Math.ceil((double) total / (double) pageSize);
    }
}
