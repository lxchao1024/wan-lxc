package com.guagua.guagua.gradle363

import android.widget.Adapter
import java.io.FileReader
import java.io.FileWriter


/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/8/26 16:58
 * Version: 1.0.0
 * Description: 1.1.1
 * History:
 * <author> <time> <version> <desc>
 */

fun main(args: Array<String>) {
    FileReader("./Text/IMG_0894.text").use { fis ->
        FileWriter("./Text/root/src.text").use { fos->
            //创建缓冲区输入流
            val bis = fis.buffered()
            //创建缓冲区输出流
            val bos = fos.buffered()
            //复制到输出流
            bis.copyTo(bos) //这个命令复制完后自动关闭流文件
            println("复制完成")
            val str = bis.readText() // 这个方法是复制文本内容到一个字符串变量中,特别注意的是这个文件读取成功后会自动关闭流文件
            val list = bis.readLines() //这个方法是读取文件内容到集合中,每一行是一个数据.读取完毕后自己关闭流文件
        }
    }
}