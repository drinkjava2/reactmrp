/* Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.reactmrp.config;

import com.github.drinkjava2.jdialects.NamingConversion;

/**
 *  本项目全局映射到数据库的规则为表名变小写，列名不变，注意没有下划线，
 *  个别要加s的以避开关键字的在实体类上用@table来调整
 */
public class ProjectNamingRule implements NamingConversion {

    @Override
    public String getTableName(Class<?> clazz) {
        return clazz.getSimpleName().toLowerCase();
    }

    @Override
    public String getColumnName(String entityField) {
        return entityField;
    }
}
