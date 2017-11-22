package com.tangshengbo.design.proxy.log;

import java.util.Map;

/**
 * Created by TangShengBo on 2017-09-12.
 */
public interface CheckService {

    Map<String, Object> recharge(String fileName);

    Map<String, Object> extract(String fileName);
}
