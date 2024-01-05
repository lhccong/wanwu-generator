package com.cong.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

/**
 * @author Cong
 */
public class MetaManager {
    private static volatile Meta meta;

    public static Meta getMeta() {
        if (meta == null) {
            synchronized (MetaManager.class) {
                if (meta == null) {
                    meta = loadMeta();
                }
            }
        }
        return meta;
    }

    private static Meta loadMeta() {
        String metaJsonStr = ResourceUtil.readUtf8Str("meta.json");
        Meta meta = JSONUtil.toBean(metaJsonStr, Meta.class);
        //校验数据是否合法
        MetaValidator.doValidateAndFill(meta);
        return meta;
    }
}
