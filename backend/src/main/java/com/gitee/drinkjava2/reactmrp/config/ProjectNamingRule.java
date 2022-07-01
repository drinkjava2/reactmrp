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
package com.gitee.drinkjava2.reactmrp.config;

import com.github.drinkjava2.jdialects.NamingConversion;

/**
 *  注意本项目实体类映射到数据库的规则为表名为类名的小写，列名不作变换，与常见的采用下划线的命名方案不同。
 *  例如 class MyOrder { String userName} 生成DDL时数据库表名为"myorder", 列名为"userName"。
 *  这种方案的优点是数据库列名与POJO属性一致，原生SQL查询结果不用再进行列名转换 
 *  
 *  个别类名与数据库关键字冲突，在实体类上用@table来个别调整，如User类加上@Table(users)，则User类映射到users表格
 *  H2在jdbcUrl中要加上DATABASE_TO_UPPER=falsec以防止它自动改成全大写。
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
