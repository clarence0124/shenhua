package com.itspub.cg

import com.itspub.util.ReflectUtils
import org.springframework.core.LocalVariableTableParameterNameDiscoverer
import java.lang.reflect.Method
import java.lang.reflect.Type
import java.nio.file.Path

/**
 * Created by Administrator on 2017/2/15.
 */
object CgUtils {

    fun indent(c : Int): String {
        return Array(c, { "  " }).joinToString(separator = "")
    }

    fun getSourcePath(srcDir: Path, classPackage: Package, className: String): Path {
        return srcDir.resolve(classPackage.name.replace(".", "\\")).resolve(className)
    }

    fun getTypeGenericClass(type: Type): java.util.List<Class<*>> {
        return ReflectUtils.getGenericParameterizedType(type)
    }

    fun getSimpleName(canonicalName: String): String {
        return canonicalName.substring(canonicalName.lastIndexOf(".") + 1)
    }

    /**
     * 获取泛型的文本描述
     */
    fun getTypeGenericName(type: Type): String? {
        val gpts = ReflectUtils.getGenericParameterizedType(type)
        return if (0 < gpts.size) {
            gpts.joinToString(separator = ",", transform = {it.simpleName})
        } else {
            null
        }
    }

    private val parameterNameDiscoverer = LocalVariableTableParameterNameDiscoverer()

    fun getParameterNames(method: Method, srcDirPath: Path? = null): Map<String?, Class<*>> {

        val paramTypes = method.parameterTypes
        val paramShortTypeNames = method.parameterTypes.map { it.simpleName }

        val paramNames: Array<String> = when {
            method.declaringClass.isInterface -> {
                if (null == srcDirPath) {
                    throw IllegalStateException("declaringClass is interface, please offer the source path")
                }

                val ktMeta = KtInterfaceMeta(srcDirPath, method.declaringClass.`package`, method.declaringClass.simpleName)

                var map = ktMeta.declaredMethods.associate {
                    val nameAndParams = it.replace("""^fun\s+""".toRegex(), "").replace("""(?<=\)).+$""".toRegex(), "")
                    val splitPos = nameAndParams.indexOf("(")
                    val name = nameAndParams.substring(0, splitPos).trimEnd()
                    val param = nameAndParams.substring(splitPos).trimStart()
                    Pair(param.substring(1, param.indexOf(")")).split(",").filter{it.trim() != ""}, name)
                }

                map = map.filter {
                    it.value == method.name && it.key.size == paramTypes.size
                }

                map = map.filter {
                    it.key.map { CgUtils.getSimpleName(it.split(":")[1].trim()) }.zip(paramShortTypeNames).any { it.first == it.second }
                }

                if (1 < map.size) {
                    throw IllegalStateException("more than one method are matched, un-support overload method")
                }

                map.flatMap { it.key.map { it.split(":")[0].trim() } }.toTypedArray()
            }
            else -> {
                parameterNameDiscoverer.getParameterNames(method)
            }
        }

        return paramNames.zip(paramTypes).toMap()
    }
}