package me.vension.mvvm.demo.http

import android.content.Context
import me.vension.mvvm.demo.R
import okhttp3.OkHttpClient
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/11 12:16.
 * @email:  2506856664@qq.com
 * @desc:   character determines attitude, attitude determines destiny
 * ========================================================
 */

object DefaultOkHttpClient{

    private const val DEFAULT_TIME_OUT = 10L //默认的超时时间是5秒钟

    fun getOKHttpClient(context: Context):OkHttpClient{
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(DEFAULT_TIME_OUT,TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT,TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT,TimeUnit.SECONDS)

        setCertificates(context,builder)//设置SSL证书
        return builder.build()
    }

    private fun setCertificates(context: Context, builder: OkHttpClient.Builder) {
        try {
            val certificateFactory = CertificateFactory.getInstance("X.509")
            val input = context.resources.openRawResource(R.raw.readhub)
            val trustStore = KeyStore.getInstance(KeyStore.getDefaultType())
            trustStore.load(null)
            trustStore.setCertificateEntry("",certificateFactory.generateCertificate(input))

            val sslContext = SSLContext.getInstance("TLS")
            val trustMamagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())

            trustMamagerFactory.init(trustStore)
            sslContext.init(null,trustMamagerFactory.trustManagers, SecureRandom())
            builder.sslSocketFactory(sslContext.socketFactory)

        }catch (e:Exception){
            e.printStackTrace()
        }
    }


}