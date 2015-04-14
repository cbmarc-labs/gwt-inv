# DATAMODEL (deprecated) #
| **programas**|| | |
|:|:|:|:|
|prg\_id|long|PK, FK software|identificador unico del programa|
|prg\_nombre|varchar(32)||nombre del programa instalado|
|prg\_tab|varchar(32)|||

| **software**|| | |
|:|:|:|:|
|sof\_pc\_id|long|PK, FK programas|Identificador del pc|
|sof\_prg\_id|long|PK, FK pc|Identificador del programa|

| **monitores**|| | |
|:|:|:|:|
|mon\_id|long|PK, FK dispositivos|Identificador unico del monitor|
|mon\_pulgadas|int||tamaño del monitor|
|mon\_pc\_asociado|long|FK pc|Ordenador personal asociado al monitor|

| **otros\_dispositivos**|| | |
|:|:|:|:|
|odisp\_id|long|PK, FK dispositivos|Identificador|
|odisp\_nombre|varchar(32)||Nombre breve del dispositivo|
|odisp\_ip|long|FK ip|Dirección del dispositivo|
|odisp\_descripcion|varchar(64)||Descripcion del dispositivo|
|odisp\_ayuda|varchar(256)|||

| **pc**|| | |
|:|:|:|:|
|pc\_id|long|PK, FK dispositivos, monitores|Identificador del ordenador personal|
|pc\_nombre|varchar(32)||Nombre del ordenador personal|
|pc\_usuario|varchar(32)||Identificador del usuario del ordenador personal|
|pc\_ip|long|FK ip|Direccion ip del pc|
|pc\_sna|varchar(16)||Direccion sna del pc|
|pc\_ram|int||Cantidad de ram del pc|
|pc\_hd|int||Cantidad de espacio de hd|
|pc\_lecto1|varchar(64)||Descripcion del primer lector del pc|
|pc\_lecto2|varchar(64)||Descripcion del segundo lector del pc|
|pc\_cpu|varchar(64)||Pequeña descripcion de la cpu del pc|
|pc\_velocidad|int||Velocidad de la cpu del pc|
|pc\_so|varchar(64)||Nombre del sistema operativo del pc|
|pc\_red|varchar(32)||Tipo de red del pc|
|pc\_formato|varchar(64)||Tipo de formato del pc|
|pc\_observaciones|varchar(256)||Observaciones generales sobre el pc|

| **impresoras**|| | |
|:|:|:|:|
|imp\_id|long|PK, FK dispositivos|Identificador de la impresora|
|imp\_nombre|varchar(32)||Nombre asignado a la impresora|
|imp\_tipo|varchar(32)||laser, matricial, ...|
|imp\_cola|varchar(32)||Nombre de la cola de impresion de red|
|imp\_ip|long|FK ip|Dirección ip de la impresora|
|imp\_puerto|varchar(16)||Nombre del puerto|
|imp\_spr||||

| **servidores\_impresion**|| | |
|:|:|:|:|
|spr\_id|long|PK, FK dispositivos|Identificador del servidor de impresion|
|spr\_nombre|varchar(32)||Nombre descriptivo|
|spr\_ip|long|FK ip|Direccion ip del servidor de impresion|
|spr\_red|varchar(16)||Tipo de red|
|spr\_puertos|int||Numero de puertos|
|spr\_prt1||||
|spr\_prt2||||

| **dispositivos**|| | |
|:|:|:|:|
|dis\_id|long|PK, FK monitores, otros\_dispositivos, pc, impresoras, servidores\_impresion, partes|Identificador|
|dis\_sn|varchar(32)||Numero de serie el dispositivo|
|dis\_nombre|varchar(32)||Nombre identificativo del dispositivo|
|dis\_marca|varchar(32)||Marca|
|dis\_modelo|varchar(32)||Modelo|
|dis\_tipo|varchar(16)||Tipo|
|dis\_centro|varchar(32)||Nombre del centro|
|dis\_sociedad|varchar(32)||Nombre de la sociedad|
|dis\_departamento|long|FK departamentos|Identificador del departamento|
|dis\_ubicacion|varchar(32)||Nombre de la ubicacion|
|dis\_fecha\_compra|datetime||Fecha de la compra|
|dis\_fin\_garantia|datetime||Fecha del fin de la garantia|
|dis\_proveedor|varchar(32)||Nombre del proveedor|
|dis\_mantenimiento|varchar(1)||Mantenimiento S/N|
|dis\_observaciones|varchar(256)||Comentarios generales|

| **ip**|| | |
|:|:|:|:|
|ip\_id|long|PK, FK otros\_dispositivos, pc, impresoras, servidores\_impresion|Identificador|
|ip\_direccion|varchar(16)||Direccion ip|
|ip\_assign|varchar(1)||Direccion assignada S/N|
|ip\_rango||||
|ip\_byte1|int||Primer byte|
|ip\_byte2|int||Segundo byte|
|ip\_byte3|int||Tercer byte|
|ip\_byte4|int||Cuarto byte|
|ip\_mascara|varchar(16)||Mascara ip|

| **partes**|| | |
|:|:|:|:|
|par\_id|long|PK, FK diario\_partes|Identificador|
|par\_atu|varchar(32)||Nombre del asistente|
|par\_fecha|date||Fecha del parte|
|par\_hora|time||Hora del parte|
|par\_numparte|varchar(32)||Numero asignado al parte|
|par\_dis\_id|long|FK dispositivos|Identificador del dispositivo|
|par\_dis\_sn||||
|par\_dis\_nombre||||
|par\_dis\_marca||||
|par\_dis\_modelo||||
|par\_dis\_tipo||||
|par\_dis\_centro||||
|par\_dis\_departamento||||
|par\_dis\_ubicacion||||
|par\_dis\_fecha\_compra||||
|par\_dis\_fin\_garantia||||
|par\_dis\_proveedor||||
|par\_dis\_mantenimiento||||
|par\_dis\_observaciones||||

| **diario\_partes**|| | |
|:|:|:|:|
|dpar\_id|long|PK|Identificador|
|dpar\_par\_id|long|FK partes|Identificador del parte|
|dpar\_fecha|date||Fecha|
|dpar\_hora|time||Hora|
|dpar\_accion|varchar(256)||Descripcion de la accion|

| **departamentos**|| | |
|:|:|:|:|
|dep\_id|long|PK, FK dispositivos|Identificador|
|dep\_nombre|varchar(32)||Nombre descriptivo|
|dep\_observaciones|varchar(256)||Observaciones generales del departamento|