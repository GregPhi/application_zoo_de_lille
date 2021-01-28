# application_zoo_de_lille

### Pour tester l'application_zoo_de_lille en phase de développement
- création d'un objet Local.java
```java
package com.example.zoodelille.data.api.service;

public class Local {
    public static final String URL = "http://IP:8080/";
}
```
- création d'une sécurité dans res/xml
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

### Crédits :
- SVG Parcours : Icons made by "https://www.flaticon.com/authors/srip" from "https://www.flaticon.com/"
- SVG Animal : Icons made by "https://www.flaticon.com/authors/freepik" from "https://www.flaticon.com/"
- SVG QR Code : Icons made by "https://www.flaticon.com/authors/freepik" from "https://www.flaticon.com/"
