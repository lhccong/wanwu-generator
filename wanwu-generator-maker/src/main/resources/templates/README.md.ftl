# ${name}

> ${description}
>
> 作者：${author}
>
> 基于 [程序员聪](https://github.com/lhccong) 的 [wanwu代码生成器项目](https://github.com/lhccong/wanwu-generator) 制作，感谢您的使用！

可以通过命令行交互式输入的方式动态生成想要的项目代码

## 使用说明

执行项目根目录下的脚本文件：

```
generator <命令> <选项参数>
```

示例命令：

```
generator generate <#list modelConfig.models as modelInfo><#if modelInfo.abbr??>-${modelInfo.abbr}</#if> </#list>
```

## 参数说明

<#list modelConfig.models as modelInfo>
        <#if modelInfo.fieldName??>
${modelInfo?index + 1}）${modelInfo.fieldName}

类型：${modelInfo.type}

描述：${modelInfo.description}



默认值：${modelInfo.defaultValue?c}

<#if modelInfo.abbr??>缩写： -${modelInfo.abbr}</#if>

</#if>
</#list>