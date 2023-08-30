package com.example.finalalarma

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import android.widget.ToggleButton
import java.util.*

class MainActivity : AppCompatActivity() {
    //se declaran las variables
    var alarmTimePicker : TimePicker? = null
    var pendingIntent: PendingIntent? = null
    var alarManager: AlarmManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
 // se declaran los  elementos del xml
        alarmTimePicker = findViewById(R.id.timePicker)
        alarManager = getSystemService(ALARM_SERVICE) as AlarmManager
    }

// metodo que activa  el toggle
    fun onToggleClicked(view: View) {
    //se define la variable y se declara de tipo long
        var time : Long
        //condicional para cuando es selecconada
        if((view as ToggleButton).isChecked){
            Toast.makeText(this, " alarma Seleccionada ", Toast.LENGTH_SHORT).show()

            // se crea el calendario
            val calendar =  Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = alarmTimePicker!!.currentHour
            calendar[Calendar.MINUTE]= alarmTimePicker!!.currentMinute

            // llamamos la clase Alarmreceiver
            val intent= Intent(this , AlarmReceiver::class.java)
            pendingIntent=PendingIntent.getBroadcast(this,0,intent,0)

            // implementa el tiempo en milisegundos
            time = calendar.timeInMillis-calendar.timeInMillis%60000

            if (System.currentTimeMillis() > time){
             //se define el tiempo  mediante un metodo del sistema am o pm
                time = if (Calendar.AM_PM == 0){
                    time  +1000*60*60*12
                }else{
                    time +1000*60*60*24
                }

            }
            alarManager!!.setRepeating(AlarmManager.RTC_WAKEUP,time,1000,pendingIntent)
        }
        else{
            alarManager!!.cancel(pendingIntent)
            Toast.makeText(this, "alarma apagada", Toast.LENGTH_SHORT).show()

        }
    }
}