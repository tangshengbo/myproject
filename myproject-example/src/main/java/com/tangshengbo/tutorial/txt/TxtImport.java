package com.tangshengbo.tutorial.txt;

import com.google.common.collect.Maps;
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
@SuppressWarnings("unchecked")
public class TxtImport<T> {

    private static final Logger logger = LoggerFactory.getLogger(TxtImport.class);

    /**
     * 从流中读取文件内容转换成对象
     *
     * @param in
     * @param entityClass
     * @param params
     * @return
     */
    public List<T> readFromStream(InputStream in, Class<T> entityClass, ImportParams params) {
        try {
            List<String> lines = IOUtils.readLines(in, params.getEncoding());
            return readFromString(lines, entityClass, params);
        } catch (Exception e) {
            throw new TxtImportException(e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    public List<T> readFromString(List<String> lines, Class<T> entityClass, ImportParams params) {
        List<T> result = new ArrayList<>();
        try {
            Map<Integer, String> titleMap = getTitleMap(lines, params);
            Map<String, TxtImportEntity> importEntityMap = getImportEntityMap(entityClass);
            int startIndex = params.getHeadRows() + 1;
            int size = lines.size();
            for (int i = startIndex; i < size; i++) {
                String[] columns = StringUtil.split(lines.get(i), params.getSeparatorChar());
                result.add(getObject(entityClass, titleMap, importEntityMap, columns));
            }
        } catch (Exception e) {
            throw new TxtImportException(e);
        }
        return result;
    }

    /**
     * 根据文件字段创建对象并设置字段值
     *
     * @param entityClass
     * @param titleMap
     * @param importEntityMap
     * @param columns
     * @return
     * @throws Exception
     */
    private T getObject(Class<T> entityClass, Map<Integer, String> titleMap, Map<String,
            TxtImportEntity> importEntityMap, String[] columns) throws Exception {
        Object object = ReflectionUtil.newInstance(entityClass);
        int size = titleMap.size();
        for (int j = 0; j < size; j++) {
            String column = columns[j];
            String title = titleMap.get(j);
            if (importEntityMap.containsKey(title)) {
                TxtImportEntity importEntity = importEntityMap.get(title);
                Object value = getValue(column, importEntity);
                Method setMethod = importEntity.getMethod();
                saveValue(object, value, setMethod);
            }
        }
        return (T) object;
    }

    /**
     * 获取所有注解实体
     *
     * @param entityClass
     * @return
     */
    private Map<String, TxtImportEntity> getImportEntityMap(Class<T> entityClass) {
        Map<String, TxtImportEntity> importEntityMap = Maps.newHashMap();
        Field[] fields = ReflectionUtil.getAccessibleFields(entityClass);
        for (Field field : fields) {
            if (!isUseTxtTitle(field)) {
                continue;
            }
            TxtImportEntity txtImportEntity = getImportEntity(field, entityClass);
            importEntityMap.put(txtImportEntity.getName(), txtImportEntity);
        }
        return importEntityMap;
    }

    /**
     * 获取注解实体
     *
     * @param field
     * @param entityClass
     */
    private TxtImportEntity getImportEntity(Field field, Class<T> entityClass) {
        TxtTitle txtTitle = field.getAnnotation(TxtTitle.class);
        TxtImportEntity txtImportEntity = new TxtImportEntity();
        txtImportEntity.setField(field);
        txtImportEntity.setName(txtTitle.value());
        Method method = ReflectionUtil.getSetMethod(entityClass, field.getName());
        if (Objects.isNull(method)) {
            throw new TxtImportException("没有找到对应的方法：" + ReflectionUtil.getSetMethodName(field.getName()));
        }
        txtImportEntity.setMethod(method);
        if (StringUtil.isNotBlank(txtTitle.format())) {
            txtImportEntity.setFormat(txtTitle.format());
        }
        return txtImportEntity;
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
     *
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
     *
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
     *
     * @param obj
     * @param value
     * @param method
     * @throws ReflectiveOperationException
     */
    private void saveValue(Object obj, Object value, Method method) throws ReflectiveOperationException {
        ReflectionUtil.invoke(obj, value, method);
    }
}
