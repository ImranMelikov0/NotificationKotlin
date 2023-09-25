package com.imranmelikov.notificationkotlin

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button=findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val builder:NotificationCompat.Builder

            val notificationController=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val intent=Intent(this,MainActivity::class.java)

            val goIntent=PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

                val channelId="channelId"
                val channelName="channelName"
                val channelFragman="channelFragman"
                val channelFirst=NotificationManager.IMPORTANCE_HIGH

                var channel:NotificationChannel?=notificationController.getNotificationChannel(channelId)

                if (channel==null){
                    channel= NotificationChannel(channelId,channelName,channelFirst)
                    channel.description=channelFragman
                    notificationController.createNotificationChannel(channel)
                }

                builder=NotificationCompat.Builder(this,channelId)

                builder.setContentTitle("Head")
                    .setContentText("body")
                    .setSmallIcon(R.drawable.baseline_ads_click_24)
                    .setContentIntent(goIntent)
                    .setAutoCancel(true)
            }else{
                builder=NotificationCompat.Builder(this)

                builder.setContentTitle("Head")
                    .setContentText("body")
                    .setSmallIcon(R.drawable.baseline_ads_click_24)
                    .setContentIntent(goIntent)
                    .setAutoCancel(true)
                    .priority=Notification.PRIORITY_HIGH
            }

            notificationController.notify(1,builder.build())
        }
    }
}