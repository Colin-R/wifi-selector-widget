package com.android.andrew.wifi_selector;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import java.util.ArrayList;
import java.util.List;

public class WifiConnectionManager {

    private final WifiManager wifiManager;

    public WifiConnectionManager( Context context){
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    public List<WifiConfigurationDecorator> getKnownWifiNetworks(){
        List<WifiConfigurationDecorator> networkIds = new ArrayList<>();
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        //No configured wifi networks
        if ( configuredNetworks == null ){
            return new ArrayList<>();
        }

        for (WifiConfiguration wifiConfig : configuredNetworks) {
            networkIds.add( new WifiConfigurationDecorator( wifiConfig ) );
        }
        return networkIds;
    }

    public void connect( WifiConfigurationDecorator wifiConfig ){
        wifiManager.enableNetwork(wifiConfig.getWifiConfig().networkId, true );
    }
}
