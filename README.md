# application_zoo_de_lille

### Pour tester l'application_zoo_de_lille en phase de développement
- création d'un objet Local.java
```java
package com.example.zoodelille.data.api.service;

public class Local {
    public static final String URL = "http://IP:8080/";
}
```
- création d'une sécurité dans res/xml/network_security_config.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">IP:8080</domain>
    </domain-config>
    <base-config cleartextTrafficPermitted="true" />
</network-security-config>
```
Où IP c'est l'adresse IP d'où vous exécutez zoo-api

- lancement de zoo-api en local
- si pas d'erreur au niveau de l'api, vous pouvez lancer l'exécution de l'application depuis AndroidStudio

Pour pouvoir utiliser la partie Map :
- création d'un fichier string/google_maps_api.xml
```xml
<resources>
    <!--
    TODO: Before you run your application, you need a Google Maps API key.

    To get one, follow this link, follow the directions and press "Create" at the end:

    https://console.developers.google.com/flows/enableapi?apiid=maps_android_backend&keyType=CLIENT_SIDE_ANDROID&r=19:D5:C9:0F:74:91:68:D8:93:14:84:2E:92:AB:60:85:AF:FD:2C:64%3Bcom.example.mapapp
    -->
    <string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">VOTRE API KEY POUR  GOOGLE MAPS API</string>
</resources>
```

### Crédits :
- SVG Parcours : Icons made by "https://www.flaticon.com/authors/srip" from "https://www.flaticon.com/"
- SVG Animal : Icons made by "https://www.flaticon.com/authors/freepik" from "https://www.flaticon.com/"
- SVG QR Code : Icons made by "https://www.flaticon.com/authors/freepik" from "https://www.flaticon.com/"
