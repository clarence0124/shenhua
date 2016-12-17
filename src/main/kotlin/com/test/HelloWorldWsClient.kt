package com.test
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory
import javax.xml.namespace.QName

/**
 * Created by Administrator on 2016/12/13.
 */
object HelloWorldWsClient {
    @JvmStatic
    fun main(args: Array<String>) {
        val dcf = JaxWsDynamicClientFactory.newInstance()
        val client = dcf.createClient("http://192.168.0.102:8777/ws/helloWorld?wsdl")

        val name = QName("http://ws.demo.itspub.com/", "sayHelloWorld")
        println(client.invoke(name))
    }
}