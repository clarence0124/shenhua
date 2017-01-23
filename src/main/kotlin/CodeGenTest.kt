import com.itspub.cg.ClassDef
import com.itspub.core.dao.AuthorityDao

/**
 * Created by Administrator on 2017/1/23.
 */
object CodeGenTest {
    @JvmStatic
    fun main(args: Array<String>) {
        println(ClassDef(AuthorityDao::class.java).gen())
    }
}