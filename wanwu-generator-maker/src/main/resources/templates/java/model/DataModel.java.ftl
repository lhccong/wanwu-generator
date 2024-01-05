package ${basePackage}.model;

import lombok.Data;

/**
* ${description}数据模型
*
* @author ${author}
* @date ${createTime}
*/
@Data
public class DataModel {
<#list modelConfig.models as modelInfo>

    <#if modelInfo.description??>
    /**
    * ${modelInfo.description}
    */
    </#if>
    private ${modelInfo.type} ${modelInfo.fieldName}<#if modelInfo.defaultValue??> = ${modelInfo.defaultValue?c}</#if>;

</#list>


}