package com.tangshengbo.design.oop.isp.book;

import java.util.Map;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public interface BookSearcher {

    void searchByAuthor();

    void searchByTitle();

    void searchByPublisher();

    void complexSearch(Map map);
}
