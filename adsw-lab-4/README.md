H2 style="text-align: center;">Laboratorio 4 - Sistema concurrente</H2>

<p style="text-align: center;">
Análisis y Diseño de Software, 2023<br>
Grado en Ingeniería de Tecnologías y Servicios de
Telecomunicación<br>
ETSI de Telecomunicación<br>
Universidad Politécnica de Madrid<br>
</p>



# Objetivos

*	Ilustrar un enfoque a procesar grandes datos
*	Desarrollar un sistema concurrente: gestión de hebras y su sincronización
*	Ilustrar la integración de algoritmos y la concurrencia
* Depurar un programa, detectar y corregir errores.

# Introducción

En algunos tipos de sistemas informáticos se requiere procesar grandes candidatos de datos, como ocurren en el ámbito de BigData. En este laboratorio y en la práctica final, se quiere ilustrar cómo se puede mejorar el rendimiento de estos sistemas. Un enfoque habitual consiste en:
* Dividir los datos en fragmentos o segmentos.
* Crear un conjunto de hebras, que ejecuten cada segmento concurrentemente.
* Combinar o integrar los resultados de cada segmento

Este enfoque permite ejecutar varias hebras en paralelo sobre los cores del procesador. Igualmente, podría extenderse con procesos, que se podrían ejecutar en varios computadores, comunicados por el paso de mensajes.

En las últimas actividades, se quiere desarrollar una estructura de datos que invierte el fichero de las registros. En concreto, se quiere obtener todas los usuarios que han estado en una localización.

# Diagrama de clases

En un fichero adjuntado, muestra el diagrama de la clases en el sistema. En la documentación de este enunciado se proporciona el fichero xxxx que incluye estas clases, donde se indica cuáles son los métodos que hay que desarrollar. Las clases están documentadas con javadoc. En el anexo se recuerda cómo se puede acceder a esta información en Eclipse.

A continuación, se describe brevemente el contenido de las clases más relevantes:
* AlmacenLab4: Encapsula la inversión de las localizaciones. Esta clase genera una estructura de datos que asocie una localización con los usuarios que han estado, al menos, una vez en cada posición. El fichero de entrada es el mismo utilizado en los laboratorios previos: registros.
* Segmento: Este clase modela un segmento. Implementa el interfaz SegmentoInterface
El segmento se representa como una lista de registros.
* Resultado: Esta clase representa el resultado generado una hebra al procesar un 
segmento. Implementa el interfaz ResultadoInterface. El resultado está representante en un diccionario, en el que la clave es una localización y el valor es una lista de los usuarios que han estado en la ella.
* Pool: crea un conjunto de hebras.
* Hebra: procesa segmentos y producen un resultado.
* MonitorSegmentos: es un monitor que sincroniza con los objetos de InvertirLocalizaciones con hebras (Hebra).

En el fichero comprimido que se proporciona incluye todo el código de las clases necesarias. Además se proporciona una biblioteca (fichero con extensión `monitor.jar`) de la clase MonitorSegmentos. De esta manera se puede ejecutar el programa, sin tener que implementar este monitor.

El resultado de la inversión de localizaciones será un diccionario, en el que la clave es una localización y el valor será la lista de los usuarios que han estado en esta posición.

Los resultados parciales se almacenan en un atributo con el mismo nombre, en la clase ```InvertirLocalizaciones```.

En este laboratorio hay que completar las clases InvertirLocalizaciones y Hebra, como se describe a continuación.

# Ejercicio 1: Completar el método procesarSegmentos de la clase AlmacenLab4

El método principal de esta clase crea todos los componentes necesarios: un pool y un monitor. El pool debe ejecutar un conjunto de hebras.

El método procesarSegmentos está encargado de procesar los segmentos: enviar los segmentos al monitor, obtener los resultados e integrar los resultados. En concreto, en este método se pide que se desarrolle el código necesario para interaccionar con el monitor


# Ejercicio 2: Desarrollar la clase Hebra.

 Una hebra debe proporcionar dos operaciones:

 * Run: se basa en un bucle que obtiene un segmento, lo procesa y lo retorna. La hebra se mantiene en este bucle hasta que el monitor ha finalizado el procesamiento. La hebra sale del bucle cuando ha finalizado la operación del monitor. Es posible que recibe un null al interaccionar con el monitor, por lo que hay comprobarlo para evitar errores.

 * procesarSegmento: Este método debe procesar un segmento, que debe invertir la información en un segmento, que se almacena en `Map<Localización, List<Integer>>`. La clave será una localización y se retornará la lista de los usuarios que han estado en una localización.

Para probarlo, se puede crear un ```main``` de la clase ```Pool```, que arranque un número determinado. Comprobad si se han creado correctamente las hebras, usando ```logs```o el depurador. El método ```procesarSegmento``` se puede probar usando un fichero de los proporcionados con registrosr. 

## Anexo 1: Generar y acceder a la documentación con Javadoc en Eclipse

La documentación existente se encuentra en la carpeta *doc* del proyecto. Para consultarla, abra el fichero *index.html* en un navegador (botón derecho \> Open with \> Web browser).

También puede consultar la documentación desde una ventana del editor de código Java. Si posiciona el ratón sobre el nombre de una clase o un método aparece una ventana auxiliar con un resumen de la documentación.

Si tiene activada la vista *Javadoc* (con Window \> Show View \> Javadoc), al hacer clic sobre el nombre de un elemento se mostrará la documentación correspondiente en la ventana correspondiente a esta vista.

Para generar o actualizar la documentación *javadoc* vaya al menú Project \> Generate Javadoc. Si aparecen errores de codificación de caracteres asegúrese de poner las opciones -encoding utf8 -docencoding utf8 -charset utf8 en el cuadro *VM options* de la tercera ventana que aparece (después de hacer Next dos veces).

## Anexo 2: Ficheros con los datos de películas

En este laboratorio sólo se usan los ficheros d

Los ficheros de las localizaciones , que se han usado en otros laboratorios /(```locationsxx.tsv```). Están compuesto de un conjunto de registros. Los ficheros tienen 
diferente tamaño. En el desarrollo, empezar a usar los ficheros más sencillos


Los ficheros ```friendsxx.tsv``` no se usan en este laboratorio.

## Anexo 3: Pruebas incrementales

Es aconsejable probar los componentes mostrados, antes de integrarlo todo. Se consigue reducir el tiempo del desarrollo del código. Las actividades propuestas son :

* Probar la creación de las hebras: poner trazas en las hebras, en el monitor y en el método ```procesarSegmentos```, para comprobar si se ejecutan e interaccionan correctamente. En las hebras, se puede usar el método ```procesarSegmentos``` que emule la invocación. Se puede usar un ```Sleep``` para simular la ejecución del código.

* Probar el ```procesarSegmento```. Desarrollarlo y probarlo aislado. En concreto, se puede leer los datos y ejecutarlo. En esta prueba, no hay que generar segmentos.

* Probar el sistema completo

## Anexo 4: Configurar el camino de ficheros y bibliotecas

### Seleccionar el directorio con los ficheros de los registros de un programa

Incialmente, se puede ejecutar el programa que se quiere, que requiere usar ficheros del ```dataset```. Lo normal es que haya un fallo de ejecución. Si ha ejecutado bien, ya está bie hecho.

Seleccionar la clase que se quiere ejecutar y seleccionar ```Run Configurations```. Selecionar la pestaña ```Arguments```. En la zona de ```Working directory```, seleccione ```other``` y seleccione ```Workspace```. En la ventana que emerge, seleccione el proyeto que se está trabajando y selecionar el directorio con los ficheros requeridos.

### Seleccionar la biblioteca ```JUnit```

Seleccione el proyecto y en el menú que emerge, seleccione ```Build path``` -> ```Configure build path```. En la ventana que aparece, seleccione ```Libraries```. En la caja que aparece, seleccionar ```Claspathh```. A continuación, seleccione el butón ```Add library```, seleccione ```JUnit```y siga las indicacions que aparecerán

### Seleccionar el fichero ```monitor.jar```

Seleccione el proyecto y en el menú que emerge, seleccione ```Build path``` -> ```Configure build path```. En la ventana que aparece, seleccione ```Libraries```. En la caja que aparece, seleccionar ```Clasapath```. A continuación, seleccione el butón ```Add External JAR```, en la ventana que aparece busque y seleccione el fichero necesario.xs
