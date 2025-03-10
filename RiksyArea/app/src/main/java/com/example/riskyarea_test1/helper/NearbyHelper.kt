package com.example.riskyarea_test1.helper;

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.messages.*


class NearbyHelper(private val context: Context) : MessageListener() {

    val messageLiveData = MutableLiveData<String>()
    val senderInRange = MutableLiveData<Int>()
    val senderWentOutsideOfRange = MutableLiveData<Int>()
    var subscribeOptions: SubscribeOptions? = null
    var publishOptions: PublishOptions? = null

    var nearbyListener: NearbyListener? = null

    fun initBluetoothOnly() {
        subscribeOptions = SubscribeOptions.Builder()
                .setStrategy(Strategy.BLE_ONLY)
                .build()
        publishOptions = PublishOptions.Builder()
                .setStrategy(Strategy.DEFAULT)
                .build()

        subscribeOptions?.let {
            Nearby.getMessagesClient(context).subscribe(this, it);
        }
    }


    fun publishMessage(value: Int) {
        val message = Message(value.toString().toByteArray())
        publishOptions?.let {
            Nearby.getMessagesClient(context).publish(message, it)
        }
    }

    override fun onFound(message: Message?) {
        val value = message?.content?.run { String(this).toInt() } ?: return
        senderInRange.postValue(value)
        nearbyListener?.onUserDiscovered(value)
        messageLiveData.postValue(String(message?.content ?: return))
    }

    override fun onLost(message: Message?) {
        val value = message?.content?.run { String(this).toInt() } ?: return
        senderWentOutsideOfRange.postValue(value)
        nearbyListener?.onUserLost(value)
    }
}


interface NearbyListener {
    fun onUserDiscovered(id: Int)
    fun onUserLost(id: Int)
}