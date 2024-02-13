# Laboratorios de la asignatura Análisis y Diseño de Software

La manera más sencilla de descargar este código es clonarlo o importarlo en el entorno de desarrollo.
También es posible importar el fichero `.zip` para cada laboratorio disponible en moodle.

## Instrucciones para Eclipse

### Primera descarga

Los proyectos de los laboratorios se pueden importar mediante la función: `File -> Import -> Projects from Git (with smart import)`.
Nos pedirá la URL de este repositorio: https://github.com/adsw-upm/adsw-laboratorios

Eclipse automáticamente descargará el repositorio en una carpeta que le especifiquemos, y procederá a buscar sub-proyectos en esa carpeta para importar
En ese punto, se pueden importar todos los proyectos e incluir la carpeta raíz también (`adsw-laboratorios`).

### Actualización

Para actualizar los enunciados, se puede actualizar el código pulsando con botón derecho en el proyecto general (`adsw-laboratorios`) `-> Team -> Pull`.
Es aconsejable refrescar los ficheros en eclipse previamente `F5` o mediante `Botón derecho -> Refresh` en el repositorio principal.

También pueden verse todos los repositorios disponibles en Eclipse mostrando la vista de repositorios en el menú: `Window -> Show View -> Others... -> Git Repositories`
Si hay algún tipo de error, se recomienda mirar el siguiente apartado.

### Resolución de conflictos / Errores en la actualización

Al intentar actualizar el repositorio es posible que nos encontremos algún error si la versión del repositorio también modifica zonas del código que nosotros hayamos modificado.
Resolver este tipo de problemas no es complicado, pero se necesitan ciertas nociones de Git que están fuera del contexto de esta asignatura.
En el error al intentar sincronizar se nos darán más detalles sobre los ficheros que causan el conflicto.
Por tanto, proponemos las siguientes soluciones generales en orden de preferencia:


Crear una copia de los ficheros conflictivos. Por ejemplo: `LectorPartidas.java` -> `LectorPartidasMio.java`.
Después, en el fichero original, pinchar con botón derecho y `Team -> Advanced -> Assume unchanged`. 
En este punto ya deberíamos poder actualizar el repositorio por el método descrito anteriormente.
Una vez actualizado el código, podremos manualmente hacer los cambios

Si la primera opción falla, siempre podremos repetir el proceso de importación del proyecto y actualizar manualmente los ficheros que hayamos modificado.
