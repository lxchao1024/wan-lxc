# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-ignorewarnings
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-dontoptimize
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-dontwarn org.keplerproject.luajava.**, android.support.v4.**, org.apache.commons.**, javax.**, org.slf4j.**,  com.google.gson**, java.awt.**, sun.misc.**, org.dom4j.**
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes *Subscribe*
#保留崩溃时候日志的行数
-keepattributes SourceFile,LineNumberTable
-dontwarn com.igexin.**
-dontwarn sdk.**
-dontwarn com.umeng.**
-dontwarn android.taobao.**
-dontwarn com.taobao.**
-keep class com.igexin.** { *; }
-keep class sdk.** { *; }
-keep class com.iflytek.**{*;}
-keep class com.umeng.** {*;}
-keep class com.taobao.** {*; }
-keep class android.taobao.** {*; }
-keep class org.greenrobot.eventbus.**{*; }
-keep public class com.guagua.sing.R$*{
	*;
}
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment

-keep class com.guagua.player.** { *; }
-keep class com.guagua.mediafilter.** { *; }
-keep class com.strumsoft.websocket.phonegap.** { *; }



-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * {
    public void set*(**);
	public void openFileChooser(android.webkit.ValueCallback,java.lang.String,java.lang.String);
	public void openFileChooser(android.webkit.ValueCallback,java.lang.String);
	public void openFileChooser(android.webkit.ValueCallback);
	public void onEvent*(**);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keep class com.alipay.android.app.IAliPay{*;}
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}

#信鸽推送
#-keep public class * extends android.app.Service
#-keep public class * extends android.content.BroadcastReceiver
-keep class com.tencent.android.tpush.**  {* ;}
-keep class com.tencent.mid.**  {*;}

#米大师
-keep class tencent.com.cftutils.**  {* ;}
-keep class com.tenpay.**  {* ;}

#jni崩溃捕获
-keep class com.github.nativehandler.**  {* ;}

#QQ登录
-keep class com.tencent.**  {* ;}

#微信
-keep class com.tencent.mm.sdk.openapi.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.openapi.** implements com.tencent.mm.sdk.openapi.WXMediaMessage$IMediaObject {*;}
-dontwarn com.tencent.mm.**
-keep class com.tencent.mm.**{*;}

#微博


#renderscript-v8
-keep class android.support.v8.renderscript.RenderScript  {* ;}


# Ensures entities remain un-obfuscated so table and columns are named correctly
#-keep class com.guagua.live.sdk.room.im.** { *; }
#sugar database


#fresco
# Keepour interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip
# Do notstrip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
   @com.facebook.common.internal.DoNotStrip *;
}
# Keepnative methods
-keepclassmembers class * {
    native <methods>;
}
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**


#-keep class com.ksy.recordlib.** {*; }
#-keep class com.ksy.statlibrary.** {*; }
-dontwarn com.ksyun.**
-keep class com.ksyun.** {*;}
-keep class com.ksy.statlibrary.** {*;}
-keep class com.ksyun.media.player.**{ *; }

-keep class org.cocos2dx.** {*; }


#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#alipay sdk
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}


#greedao sqlite
-keep class com.guagua.live.sdk.room.green.** {*;}
-keep class com.guagua.live.sdk.room.greendao.** {*;}

-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
    public static java.lang.String TABLENAME;
}
-keep class **$Properties

#rxjava
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-keep class com.guagua.avcapture.** {*;}
#ijk
-keep class tv.danmaku.ijk.media.player.** {*; }
-keep class tv.danmaku.ijk.media.player.IjkMediaPlayer{
*;
}
-keep class tv.danmaku.ijk.media.player.ffmpeg.FFmpegApi{
*;
}

-keepattributes Signature
-keep class sun.misc.Unsafe {*;}
-keep class com.google.gson.examples.android.model.** {*;}

-keep class com.heepay.plugin.api.** {*; }
##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.guagua.sing.bean.** { *; }
-keep class com.guagua.http.rs.** { *; }

##---------------End: proguard configuration for Gson  ----------
# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep public class * implements java.io.Serializable {*;}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keep public class com.guagua.sing.R$*{
public static final int *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class com.umeng.commonsdk.** {*;}

-keep class com.tencent.mm.opensdk.** {

   *;

}


-keep class com.tencent.wxop.** {
   *;
}

-keep class com.tencent.mm.sdk.** {

   *;

}
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep class com.tencent.android.tpush.** {* ;}
-keep class com.tencent.mid.** {* ;}
-keep class com.qq.taf.jce.** {*;}

    #aq
    -keep class com.guagua.aqlib.inter.** {*;}
    -keep class com.guagua.aqlib.AQListener {*;}



    #rxjava
    -dontwarn sun.misc.**
    -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
       long producerIndex;
       long consumerIndex;
    }
    -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
        rx.internal.util.atomic.LinkedQueueNode producerNode;
    }
    -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
        rx.internal.util.atomic.LinkedQueueNode consumerNode;
    }


    #fresco
    -keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip
    -keep @com.facebook.common.internal.DoNotStrip class *
    -keepclassmembers class * {
       @com.facebook.common.internal.DoNotStrip *;
    }

    -keep class io.agora.**{*;}
    -keep class com.wonderkiln.blurkit.** { *; }
    -keep class com.guagua.live.sdk.KtvPlayer{*;}
    -keep class com.guagua.live.sdk.RecordPlayer{*;}
    -keep class com.guagua.sing.lib.score.Pitch{*;}
    -keep class master.flame.danmaku.** {*;}
    -keep class com.guagua.live.sdk.bean.** { *; }
    -keep class com.guagua.live.sdk.recordbean.** { *; }

    -dontwarn android.support.v8.renderscript.*
    -keepclassmembers class android.support.v8.renderscript.RenderScript {
      native *** rsn*(...);
      native *** n*(...);
    }

    #闪验
    -ignorewarnings
    -dontwarn com.baidu.**
    -dontwarn com.tencent.**
    -dontwarn com.cmic.sso.sdk.**
    -keep class com.cmic.sso.sdk.**{*;}
    -dontwarn com.sdk.**
    -keep class com.sdk.** { *;}
    -keep class cn.com.chinatelecom.account.api.**{*;}

#openinstall
-dontwarn com.fm.openinstall.**
-keep public class com.fm.openinstall.* {*; }
-keep public interface com.fm.openinstall.* {*; }

     #okhttp3-loginterceptor
     -dontwarn com.parkingwang.okhttp3.LogInterceptor.formatter.*

#okhttp3-loginterceptor
-dontwarn com.parkingwang.okhttp3.LogInterceptor.formatter.*

    #SVGA
    -keep class com.squareup.wire.** { *; }
    -keep class com.opensource.svgaplayer.proto.** { *; }

-keep class com.guagua.sing.db.** {*;}
 -keep class com.guagua.sing.bean.** { *; }
 -keep class com.guagua.ktv.bean.** { *; }
 -keep class com.guagua.sing.http.** { *; }

 -keep class com.guagua.ktv.event.** { *; }

 -keep class  com.guagua.sing.lib.GGRecordManager{*;}
 -keep class  com.guagua.live.sdk.AccompanyDecoder{*;}
 -keep class  com.guagua.ktv.socket.SocketConstant{*;}

 -keep class com.google.protobuf.** { *; }
 -keep public class * extends com.google.protobuf.** { *; }

#greendao
-keep class org.greenrobot.greendao.**{*;}
-keep public interface org.greenrobot.greendao.**
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
-keep class net.sqlcipher.** {*;}
-keep class net.sqlcipher.database.** {*;}
-dontwarn net.sqlcipher.database.**
-dontwarn org.greenrobot.greendao.**

#ijkplayer
-keep class com.zlm.player.**
-keepclassmembers class com.zlm.player.** {
   public *;
}
-keep class tv.danmaku.ijk.media.player.** { *; }
-keep class com.zlm.hp.lyrics.** { *; }

 -keep class **$Properties
 -keep class net.sqlcipher.database.**{*;}
 -keep public interface net.sqlcipher.database.**
 -dontwarn net.sqlcipher.database.**
 -dontwarn org.greenrobot.greendao.**

 -keep class com.bun.miitmdid.core.** {*;}


#华为推送混淆
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class com.hianalytics.android.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}
-keep class com.huawei.gamebox.plugin.gameservice.**{*;}
-keep public class com.huawei.android.hms.agent.** extends android.app.Activity { public *; protected *; }
-keep interface com.huawei.android.hms.agent.common.INoProguard {*;}
-keep class * extends com.huawei.android.hms.agent.common.INoProguard {*;}

#vivo
-dontwarn com.vivo.push.**
-keep  class com.vivo.push.**{*;}
-keep  class io.rong.push.platform.vivo.VivoPushMessageReceiver{*;}

#Oppo
-keep  public class * extends android.app.Service

#xiaomi
#这里com.xiaomi.mipushdemo.DemoMessageRreceiver改成app中定义的完整类名
-keep class io.rong.push.platform.mi.MiMessageReceiver {*;}
#可以防止一个误报的 warning 导致无法成功编译，如果编译使用的 Android 版本是 23。
-dontwarn com.xiaomi.push.**

#RongCloud SDK------------------
-keepattributes Exceptions,InnerClasses
#RongCloud SDK
-keep class io.rong.** {*;}
-keep class cn.rongcloud.** {*;}
-keep class * implements io.rong.imlib.model.MessageContent {*;}
-dontwarn io.rong.push.**
-dontnote com.xiaomi.**
-dontnote com.google.android.gms.gcm.**
-dontnote io.rong.**
#RongCloud SDK----------------------

#-------极验------------
-dontwarn com.geetest.deepknow.**
-keep class com.geetest.deepknow.** {
*;
}
-dontwarn com.geetest.mobinfo.**
-keep class com.geetest.mobinfo.** {
*;
}
-dontwarn com.bangcle.andJni.**
-keep class com.bangcle.andJni.** {
*;
}
#-------极验------------

#-----------vlayout---------------
-keep class com.alibaba.android.vlayout.ExposeLinearLayoutManagerEx { *; }
-keep class android.support.v7.widget.RecyclerView$LayoutParams { *; }
-keep class android.support.v7.widget.RecyclerView$ViewHolder { *; }
-keep class android.support.v7.widget.ChildHelper { *; }
-keep class android.support.v7.widget.ChildHelper$Bucket { *; }
-keep class android.support.v7.widget.RecyclerView$LayoutManager { *; }
#-----------vlayout---------------

#-----------数字联盟---------------
-keep class cn.shuzilm.core.**{*;}
#-----------即构--------------
-keep class com.zego.**{*;}
#-----------七牛--------------
-keep class com.pili.pldroid.player.** { *; }
-keep class com.qiniu.qplayer.mediaEngine.MediaPlayer{*;}
#-----------阿里实人认证-------
-keep class com.taobao.securityjni.**{*;}
-keep class com.taobao.wireless.security.**{*;}
-keep class com.ut.secbody.**{*;}
-keep class com.taobao.dp.**{*;}
-keep class com.alibaba.wireless.security.**{*;}
-keep class com.alibaba.security.rp.**{*;}
-keep class com.alibaba.sdk.android.**{*;}
-keep class com.alibaba.security.biometrics.**{*;}
-keep class android.taobao.windvane.**{*;}
-keepclassmembers class **{ public static com.meituan.robust.ChangeQuickRedirect *; }

