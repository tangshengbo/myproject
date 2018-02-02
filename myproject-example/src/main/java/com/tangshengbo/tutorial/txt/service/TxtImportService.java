package com.tangshengbo.tutorial.txt.service;

import com.google.common.collect.Maps;
import com.tangshengbo.tutorial.txt.annotation.TxtTitle;
import com.tangshengbo.tutorial.txt.entity.ImportParams;
import com.tangshengbo.tutorial.txt.entity.TxtImportEntity;
import com.tangshengbo.tutorial.txt.util.ReflectionUtil;
import jodd.util.ReflectUtil;
import jodd.util.StringUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Tangshengbo on 2018/2/2.
 */
public class TxtImportService {

    private static final Logger logger = LoggerFactory.getLogger(TxtImportService.class);

    @SuppressWarnings("unchecked")
    public <T> List<T> importTxtToList(InputStream in, Class<?> entityClass, ImportParams params) {
        List result = new ArrayList();
        try {
            List<String> lines = IOUtils.readLines(in, params.getEncoding());
            Map<Integer, String> titleMap = getTitleMap(lines, params);
            Map<String, TxtImportEntity> importEntityMap = getImportEntityMap(entityClass);
            for (int i = params.getStartIndex(); i < lines.size(); i++) {
                String[] columns = StringUtil.split(lines.get(i), params.getSeparatorChar());
                Object object = ReflectUtil.newInstance(entityClass);
                for (int j = 0; j < titleMap.size(); j++) {
                    String column = columns[j];
                    String title = titleMap.get(j);
                    if (importEntityMap.containsKey(title)) {
                        TxtImportEntity importEntity = importEntityMap.get(title);
                        Object value = getValue(column, importEntity);
                        saveValue(object, value, importEntity.getMethod());
                        result.add(object);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("导入文本文件异常{}", e);
        } finally {
            IOUtils.closeQuietly(in);
        }
        return result;
    }

    /**
     *
     * @param entityClass
     * @return
     */
    private Map<String, TxtImportEntity> getImportEntityMap(Class<?> entityClass) {
        Map<String, TxtImportEntity> importEntityMap = Maps.newHashMap();
        Field[] fields = ReflectionUtil.getAccessibleFields(entityClass);
        for (Field field : fields) {
            if (!isUseTxtTitle(field)) {
                continue;
            }
            addEntityToMap(field, entityClass, importEntityMap);
        }
        return importEntityMap;
    }

    /**
     * 获取注解实体
     * @param field
     * @param entityClass
     * @param importEntityMap
     */
    private void addEntityToMap(Field field, Class<?> entityClass, Map<String, TxtImportEntity> importEntityMap) {
        TxtTitle txtTitle = field.getAnnotation(TxtTitle.class);
        TxtImportEntity txtImportEntity = new TxtImportEntity();
        txtImportEntity.setField(field);
        txtImportEntity.setName(txtTitle.value());
        txtImportEntity.setMethod(ReflectionUtil.getSetMethod(entityClass,field.getName()));
        if (StringUtil.isNotBlank(txtTitle.format())) {
            txtImportEntity.setFormat(txtTitle.format());
        }
        importEntityMap.put(txtImportEntity.getName(), txtImportEntity);
    }

    /**
     * 判断是否使用标题注解
     *
     * @param field
     * @return
     */
    private boolean isUseTxtTitle(Field field) {
        return Objects.nonNull(field.getAnnotation(TxtTitle.class));
    }

    /**
     * 获取标题行
     * @param lines
     * @param params
     * @return
     */
    private Map<Integer, String> getTitleMap(List<String> lines, ImportParams params) {
        String title = lines.get(params.getHeadRows());
        String[] titles = StringUtil.split(title, params.getSeparatorChar());
        Map<Integer, String> titleMap = Maps.newHashMap();
        for (int i = 0; i < titles.length; i++) {
            titleMap.put(i, titles[i]);
        }
        return titleMap;
    }


    /**
     * 获取文件字段值
     * @param column
     * @param entity
     * @return
     * @throws Exception
     */
    private Object getValue(String column, TxtImportEntity entity) throws Exception {
        String classType;
        Method setMethod = entity.getMethod();
        Type[] ts = setMethod.getGenericParameterTypes();
        classType = ts[0].toString();
        return ReflectionUtil.getValueByType(classType, column, entity);
    }

    /**
     * 设置字段值
     * @param obj
     * @param value
     * @param method
     * @throws ReflectiveOperationException
     */
    private void saveValue(Object obj, Object value, Method method) throws ReflectiveOperationException {
        ReflectionUtil.invoke(obj, value, method);
    }
}
