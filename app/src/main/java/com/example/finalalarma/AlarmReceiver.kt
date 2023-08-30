package com.example.finalalarma

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.drm.DrmStore.RightsStatus
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Vibrator
import android.widget.Toast
 //clase para definir el sonnido
class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        //se declara la variable intanciada a el metodo Vibrator ed tipo vibrator
        val vibrator = context!!.getSystemService(Context.VIBRATOR_SERVICE)as Vibrator
        vibrator.vibrate(200) //es el tiempo de duracion
        Toast.makeText(context, "LA ALARMA ESTA ACTIVA", Toast.LENGTH_LONG).show()
// se define el tipo de sonido intanciando a una variable y con el metodo rigtonemanager hacemos uso de sus metodos
        var alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        if (alarmUri==null){
            alarmUri=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        }
        var rigntone = RingtoneManager.getRingtone(context,alarmUri)
        rigntone.play()
    }
}